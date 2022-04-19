package com.lzw.store.service.impl;

import com.lzw.store.entity.User;
import com.lzw.store.mapper.UserMapper;
import com.lzw.store.service.IUserService;
import com.lzw.store.service.ex.InsertException;
import com.lzw.store.service.ex.PasswordNotMatchException;
import com.lzw.store.service.ex.UserNotFoundException;
import com.lzw.store.service.ex.UsernameDuplitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service.impl
 * @Author: lzw
 * @CreateTime: 2022-04-07 22:49
 * @Description: 用户模块业务层的实现类
 */
@Service // @Service注解： 将当前类的对象交给Spring来管理，自动创建对象以及维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 调用selectByUsername判断次用户名是否已注册
        String username = user.getUsername();
        User result = userMapper.selectByUsername(username);
        // 判断结果集是否部位null则抛出用户名被占用的异常
        if (result != null){
            // 抛出异常
            throw new UsernameDuplitedException("用户名已被占用");
        }

        // 密码加密处理的实现：md5算法的形式:67dhdsgh-sadasd-asdsa9
        // （串 + password + 串） ---- md5算法进行加密，连续加载三次
        // 盐值 + password + 盐值 ---- 盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        // 获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        // 将密码和盐值作为一个整体进行加密处理, 忽略原有密码的强度提升了密码的安全性
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);

        // 补全数据：数据表其他字段
        // is_delete设置成0
        user.setIsDelete(0);
        // 四个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // 执行注册业务功能的实现(此时依然有可能出现异常)
        Integer rows = userMapper.insertUser(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
        User result = userMapper.selectByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        // 检测用户的密码是否匹配
        // 1.先获取到数据库中的加密之后的密码
        String oldPassword = result.getPassword();

        // 2.和用户的传递过来的密码进行比较

        // 2.1 先获取盐值：上一次在注册时所自动生成的盐值
        String salt = result.getSalt();
        // 2.2 将用户的密码按照想用的md5算法的规则进行加密
        String newMd5Password = getMD5Password(password, salt);
        // 3.讲密码进行比较
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        // 判断is_delete字段的值是否为1，1：表示被标记删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 成功登陆后返回对应用户的数据
        // 重新封装用户对象，返回页面需要的信息
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    /**
     * 定义一个md5算法的加密处理
     */
    private String getMD5Password (String password, String salt){
        // md5加密算法方法的调用(进行三次加密)
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

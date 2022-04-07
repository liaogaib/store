package com.lzw.store.service.impl;

import com.lzw.store.entity.User;
import com.lzw.store.mapper.UserMapper;
import com.lzw.store.service.IUserService;
import com.lzw.store.service.ex.InsertException;
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

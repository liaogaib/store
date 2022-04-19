package com.lzw.store.service;

import com.lzw.store.entity.User;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service
 * @Author: lzw
 * @CreateTime: 2022-04-07 22:45
 * @Description: 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     *
     * @param username 用户名
     * @param password  用户的密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     */
    User login(String username, String password);
}

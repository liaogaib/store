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
}

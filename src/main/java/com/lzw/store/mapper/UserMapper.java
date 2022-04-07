package com.lzw.store.mapper;

import com.lzw.store.entity.User;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.mapper
 * @Author: lzw
 * @CreateTime: 2022-04-05 18:23
 * @Description: 用户模块的持久层接口
 */
public interface UserMapper {
    /**
     *
     * @param user 用户的数据
     * @return 受影响的行数（增、删、改，都有受影响的行数作为返回值，可以根据返回值来是否执行成功）
     */
    Integer insertUser(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null值
     */
    User selectByUsername(String username);
}

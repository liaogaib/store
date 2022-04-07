package com.lzw.store.entity;



import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.entity
 * @Author: lzw
 * @CreateTime: 2022-04-04 12:07
 * @Description: user实体类
 */
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}

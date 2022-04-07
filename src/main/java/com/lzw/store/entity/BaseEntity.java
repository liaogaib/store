package com.lzw.store.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.entity
 * @Author: lzw
 * @CreateTime: 2022-04-04 11:59
 * @Description: 实体类基类
 */
@Data
public class BaseEntity {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}

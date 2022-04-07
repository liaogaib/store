package com.lzw.store.service.ex;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service.ex
 * @Author: lzw
 * @CreateTime: 2022-04-07 22:20
 * @Description: 业务层异常的基类
 */
// 业务异常仅在运行时才会抛出，则使其继承于RuntimeException

/**
 * throw new ServiceException("业务层产生未知的异常")
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    // 仅抛出异常信息
    public ServiceException(String message) {
        super(message);
    }

    // 不仅抛出异常信息，还将异常对象抛出
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

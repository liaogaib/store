package com.lzw.store.service.ex;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service.ex
 * @Author: lzw
 * @CreateTime: 2022-04-19 22:10
 * @Description: 用户无法找到异常
 */
public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

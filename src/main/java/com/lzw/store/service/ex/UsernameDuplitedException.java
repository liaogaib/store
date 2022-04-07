package com.lzw.store.service.ex;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service.ex
 * @Author: lzw
 * @CreateTime: 2022-04-07 22:37
 * @Description: 用户名被占用异常
 */
public class UsernameDuplitedException extends ServiceException {
    public UsernameDuplitedException() {
        super();
    }

    public UsernameDuplitedException(String message) {
        super(message);
    }

    public UsernameDuplitedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplitedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplitedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

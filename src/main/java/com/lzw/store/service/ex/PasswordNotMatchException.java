package com.lzw.store.service.ex;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.service.ex
 * @Author: lzw
 * @CreateTime: 2022-04-19 22:08
 * @Description: 用户密码无法匹配异常
 */
public class PasswordNotMatchException extends ServiceException {
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package ru.kirilushkin.personalaccount.exception;

public class AccountNotFounException extends RuntimeException {
    public AccountNotFounException() {
        super();
    }

    public AccountNotFounException(String message) {
        super(message);
    }

    public AccountNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFounException(Throwable cause) {
        super(cause);
    }

    protected AccountNotFounException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

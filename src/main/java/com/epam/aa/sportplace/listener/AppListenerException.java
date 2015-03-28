package com.epam.aa.sportplace.listener;

public class AppListenerException extends RuntimeException {
    public AppListenerException() {
    }

    public AppListenerException(String message) {
        super(message);
    }

    public AppListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppListenerException(Throwable cause) {
        super(cause);
    }
}

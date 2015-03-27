package com.epam.aa.sportplace.listener;

/**
 * Created by almas on 28/03/2015.
 */
public class ServerInitException extends RuntimeException {
    public ServerInitException() {
    }

    public ServerInitException(String message) {
        super(message);
    }

    public ServerInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerInitException(Throwable cause) {
        super(cause);
    }
}

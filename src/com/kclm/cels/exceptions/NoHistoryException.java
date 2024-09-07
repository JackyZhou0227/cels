package com.kclm.cels.exceptions;

public class NoHistoryException extends Exception {

    public NoHistoryException() {
        super();
    }

    public NoHistoryException(String message) {
        super(message);
    }

    public NoHistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHistoryException(Throwable cause) {
        super(cause);
    }
}

package com.kclm.cels.exceptions;

public class NoTestRecordException extends Exception {

    public NoTestRecordException() {
        super();
    }

    public NoTestRecordException(String message) {
        super(message);
    }

    public NoTestRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTestRecordException(Throwable cause) {
        super(cause);
    }
}

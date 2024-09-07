package com.kclm.cels.exceptions;

public class NoNoteException extends Exception {

    public NoNoteException() {
        super();
    }

    public NoNoteException(String message) {
        super(message);
    }

    public NoNoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNoteException(Throwable cause) {
        super(cause);
    }
}

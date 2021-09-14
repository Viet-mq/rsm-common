package com.edso.resume.lib.exception;

public class ResponseDecodeException extends Exception {

    public ResponseDecodeException() {
    }

    public ResponseDecodeException(String message) {
        super(message);
    }

    public ResponseDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseDecodeException(Throwable cause) {
        super(cause);
    }

    public ResponseDecodeException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

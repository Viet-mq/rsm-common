package com.edso.resume.lib.exception;

public class ServerException extends AbstractException {
    public ServerException() {
        super(500);
    }

    public ServerException(ErrorCode errorCode) {
        super(500, errorCode);
    }

    public ServerException(int httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}
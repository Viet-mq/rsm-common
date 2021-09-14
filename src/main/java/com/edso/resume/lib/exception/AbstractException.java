package com.edso.resume.lib.exception;

public class AbstractException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;

    private final int code;

    private final String message;

    private final String detailMessage;

    public AbstractException(int status, ErrorCode errorCode) {
        this.status = status;
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detailMessage = errorCode.getDetailMessage();
    }

    public AbstractException(int status) {
        this.status = status;
        code = 0;
        message = null;
        detailMessage = null;
    }

    public int getStatus() {
        return status;
    }

    public ErrorCode getCode() {
        return new ErrorCode(code, message, detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

}


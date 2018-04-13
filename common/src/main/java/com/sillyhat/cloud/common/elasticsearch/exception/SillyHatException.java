package com.sillyhat.cloud.common.elasticsearch.exception;

public class SillyHatException extends Exception {

    private String requestId;

    private Integer statusCode;

    public SillyHatException(String message, String requestId, Integer statusCode) {
        super(message, (Throwable)null);
        this.requestId = requestId;
        this.statusCode = statusCode;
    }

    public SillyHatException(String message, String requestId, Integer statusCode, Throwable e) {
        super(message, e);
        this.statusCode = statusCode;
        this.requestId = requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        String reqIdStr = "";
        if (this.requestId != null) {
            reqIdStr = "; requestId: " + this.requestId;
        }
        return super.toString() + reqIdStr;
    }
}

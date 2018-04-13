package com.sillyhat.cloud.common.elasticsearch.exception;

public class SillyHatElasticsearchException extends SillyHatException {

    private static final long serialVersionUID = 8060277310864851836L;

    public SillyHatElasticsearchException(String message) {
        super(message, null, 0);
    }

    public SillyHatElasticsearchException(String message, Throwable e) {
        super(message, null, 0, e);
    }
}

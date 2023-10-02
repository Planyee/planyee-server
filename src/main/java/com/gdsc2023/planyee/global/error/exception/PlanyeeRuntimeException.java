package com.gdsc2023.planyee.global.error.exception;

public class PlanyeeRuntimeException extends RuntimeException {

    private final String messageKey;
    private final Object[] params;

    public PlanyeeRuntimeException(String messageKey, Object[] params) {
        this.messageKey = messageKey;
        this.params = params;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getParams() {
        return params;
    }
}

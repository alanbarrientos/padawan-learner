package com.alan;

public class Response <T>{
    private boolean isSuccessful;
    private String message;
    private int code;
    private T body;

    public Response(boolean isSuccessful, String message, int code) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.code = code;
        this.body = null;
    }
    public Response(boolean isSuccessful, String message, int code, T body) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.code = code;
        this.body = body;
    }


    public boolean isSuccessful() {
        return isSuccessful;
    }


    public String getMessage() {
        return message;
    }


    public int getCode() {
        return code;
    }

    public T getBody() {
        return body;
    }
}

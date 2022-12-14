package com.example.springbootexample.dto;

public class GenericResponse<T> {
    private String code;
    private String message;
    private T date;

    public GenericResponse(String code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public GenericResponse() {

    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

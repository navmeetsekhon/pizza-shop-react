package com.orderManagement.orderManagementApp.utils;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private int code = HttpStatus.OK.value();
    private T data;
    private String message;
    private String errorCode;

    private ApiResponse() {}
    public ApiResponse(int code){
        this.code = code;
    }
    public ApiResponse(int code,T data){
        this.code = code;
        this.data = data;
    }
    public ApiResponse(int code,String message){
        this.code = code;
        this.message = message;
    }
    public ApiResponse(int code , T data , String message , String errorCode){
        this.code = code;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiResponse(int code, String message, String errorCode) {
        this.code = code;
        this.message = message;
        this.errorCode = errorCode;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}

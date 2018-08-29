package com.mm.blog.response;

import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @Auther: mm
 * @Date: 2018/8/27 16:15
 * @Description:
 */
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = -7455593550812620503L;
    /** response status code */
    private String statusCode;

    /** response error message */
    private String message;

    /** response data */
    private T data;

    public APIResponse(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
    public APIResponse() {

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * build a ApiResponse.
     *
     * @param responseStatus
     * @param data
     * @return
     */
    public static <T> APIResponse<T> build(@NonNull ResponseStatus responseStatus, T data) {
        return new APIResponse<T>(responseStatus.getCode(), responseStatus.getMessage(), data);
    }
}

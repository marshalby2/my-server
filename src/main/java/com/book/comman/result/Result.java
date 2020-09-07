package com.book.comman.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:08 AM
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1581330846770173770L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;


    @Getter
    @Setter
    private T data;

    public static <T> Result<T> success() {
        return restResult(null, ResultCode.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, ResultCode.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> success(T data, String message) {
        return restResult(data, ResultCode.SUCCESS.getCode(), message);
    }

    public static <T> Result<T> failed() {
        return restResult(null, ResultCode.SYSTEM_ERROR.getCode(), null);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, ResultCode.SYSTEM_ERROR.getCode(), null);
    }

    public static <T> Result<T> failed(T data, String message) {
        return restResult(data, ResultCode.SYSTEM_ERROR.getCode(), message);
    }

    /**
     * 未登录
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> unauthorized(T data) {
        return restResult(data, ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage());
    }

    /**
     * 未授权
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> forbidden(T data) {
        return restResult(data, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
    }

    private static <T> Result<T> restResult(T data, int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}

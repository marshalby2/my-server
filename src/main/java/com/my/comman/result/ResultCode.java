package com.my.comman.result;

import lombok.Getter;

/**
 * @Description 返回状态码
 * @Author marshal
 * @Date 5/9/20 9:52 AM
 */
public enum ResultCode {

    /**
     * 请求成功
     */
    SUCCESS("success", 200),

    /**
     * 业务逻辑错误，或调用参数不正确
     */
    PARAMETER_ERROR("业务限制或请求参数不正确", 501),

    /**
     * 未登录，Token过期
     */
    UNAUTHORIZED("未登录或Token过期", 401),

    /**
     * 拒绝访问，没有相关权限
     */
    FORBIDDEN("没有相关权限", 403),

    /**
     * 请求资源不存或者URL错误
     */
    NOT_FOUND("资源不存在或URL错误", 404),

    /**
     * 服务器内部错误
     */
    SYSTEM_ERROR("服务器繁忙，稍后重试", 500),

    /**
     * 网关错误,无法提供任何服务支持
     */
    GATEWAY_ERROR("网关错误", 502),

    /**
     * 服务不可用,子服务请求超时
     */
    SERVICE_UNAVAILABLE("请求超时或服务不可用", 503);

    /**
     * 返回消息
     */
    @Getter
    private String message;

    /**
     * 状态码
     */
    @Getter
    private int code;

    ResultCode(String message, int code) {
        this.message = message;
        this.code = code;
    }
}

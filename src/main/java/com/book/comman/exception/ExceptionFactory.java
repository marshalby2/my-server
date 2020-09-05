package com.book.comman.exception;

/**
 * 系统异常工具类
 *
 */
public class ExceptionFactory {

    /**
     * 基础自定义异常
     *
     * @param message 错误消息
     * @param args    错误参数
     * @return
     * @throws BaseException 基础异常类
     */
    public static BaseException build(String message, Object... args) {
        if (args != null && args.length > 0) {
            message = String.format(message, args);
        }
        throw new BaseException(message);
    }

    /**
     * 基础自定义异常
     *
     * @param message 错误消息
     * @return 基础异常类
     */
    public static BaseException build(String message) throws BaseException {
        throw new BaseException(message);
    }

}

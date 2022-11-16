package com.example.springbootexample.controller;

import com.example.springbootexample.dto.GenericResponse;
import com.example.springbootexample.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * @ControllerAdvice结合
 * @ExceptionHandler
 * 即可实现全局异常捕获
 */
@ControllerAdvice
public class ControllerAdviceProcessor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GenericResponse<?> handleException(HttpServletRequest request,
                                              Exception ex) {


        String code = "500";
        if (ex instanceof HttpMessageNotReadableException) {
            code = "400";
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            code = "999";
        }
        String msg = null;

        if (ex instanceof BizException) {
            BizException bizException = (BizException) ex;
            msg = bizException.getMessage();
            code = bizException.getErrorCode().getCode();

        } else if (ex instanceof AccessDeniedException) {
            msg = "无权限访问";
            code = "403";
        }
        if (msg == null) {
            msg = ex.getMessage();
        }
        GenericResponse<?> resp = new GenericResponse<>();
        resp.setCode(code);
        resp.setMessage(msg);
        logger.error("code: " + code + "  msg: " + msg, ex);
        return resp;
    }
}

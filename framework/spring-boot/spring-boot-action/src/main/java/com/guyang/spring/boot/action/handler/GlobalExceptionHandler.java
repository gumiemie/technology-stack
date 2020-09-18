package com.guyang.spring.boot.action.handler;

import com.guyang.spring.boot.core.dto.RespDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 全局异常处理
 * @date 2020-07-26 17:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public RespDto handleConstraintViolationException(ConstraintViolationException e) {
        return new RespDto(400, e.getMessage());
    }


}

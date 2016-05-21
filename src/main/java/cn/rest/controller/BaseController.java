package cn.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.rest.exception.ErrorCode;
import cn.rest.exception.SystemException;
import cn.rest.response.ResponseUtils;

@ControllerAdvice
public class BaseController {
    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException e) {
        return ResponseUtils.get(e, e.getLocalizedMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        return ResponseUtils.get(ErrorCode.UnknownError,
                e.getLocalizedMessage());
    }
}

package cn.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.rest.util.ResponseUtils;

@ControllerAdvice
public class BaseController {
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        return ResponseUtils.get(50000, e.getLocalizedMessage(), null);
    }
}

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
        return ResponseUtils.get(e);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        String msg = e.getLocalizedMessage();
        if(msg!=null){
            return ResponseUtils.get(ErrorCode.UnknownError,msg);
        }
        String name = e.toString();
        if(name!=null){
            return ResponseUtils.get(ErrorCode.UnknownError,name);
        }
        return ResponseUtils.get(ErrorCode.UnknownError);
    }
}

package cn.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.rest.exception.ErrorCode;
import cn.rest.exception.ServiceException;
import cn.rest.exception.SystemException;
import cn.rest.response.ResponseUtils;

@ControllerAdvice
public class BaseController {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException e) {
        return ResponseUtils.get(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException e) {
        String msg = e.getLocalizedMessage();
        String name = e.toString();
        return ResponseUtils.get(ErrorCode.SystemError.getCode(),
                msg == null ? name : msg);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        String msg = e.getLocalizedMessage();
        if (msg != null) {
            return ResponseUtils.get(ErrorCode.UnknownError.getCode(), msg);
        }
        String name = e.toString();
        if (name != null) {
            return ResponseUtils.get(ErrorCode.UnknownError.getCode(), name);
        }
        return ResponseUtils.get(ErrorCode.UnknownError.getCode(),
                ErrorCode.UnknownError.getMessage());
    }
}

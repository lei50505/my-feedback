package cn.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cn.rest.exception.ErrorCode;
import cn.rest.exception.ServiceException;
import cn.rest.exception.SystemException;

public class ResponseUtils {
    private ResponseUtils() {
    }

    public static <T> ResponseEntity<Object> get(int code, String message,
            T data) {
        ResponseDto<T> responseDto = new ResponseDto<T>(code, message, data);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(
                responseDto, HttpStatus.OK);
        return responseEntity;
    }

    public static <T> ResponseEntity<Object> get(T data) {
        return get(20000, null, data);
    }

    public static <T> ResponseEntity<Object> get() {
        return get(20000, null, null);
    }

    public static <T> ResponseEntity<Object> get(ServiceException e) {
        return get(e.getCode(), e.getMessage(), null);
    }
    public static <T> ResponseEntity<Object> get(SystemException e) {
        String msg = e.getLocalizedMessage();
        String name = e.toString();
        return get(ErrorCode.SystemError, msg==null?name:msg);
    }

    public static <T> ResponseEntity<Object> get(ErrorCode e) {
        return get(e.getCode(), e.getMessage(), null);
    }

    public static <T> ResponseEntity<Object> get(ErrorCode e, String message) {
        return get(e.getCode(), message, null);
    }

}

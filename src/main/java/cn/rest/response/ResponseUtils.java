package cn.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public static <T> ResponseEntity<Object> get(int code,String message) {
        return get(code, message, null);
    }

}

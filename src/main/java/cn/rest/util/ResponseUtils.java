package cn.rest.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtils {
    private ResponseUtils(){}
    public static <T> ResponseEntity<Object> get(int code,String message,T data){
        ResponseDto<T> responseDto = new ResponseDto<T>(code, message, data);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseDto,HttpStatus.OK);
        return responseEntity;
    }
}

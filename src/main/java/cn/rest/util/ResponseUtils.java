package cn.rest.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cn.rest.exception.ErrorCode;


public class ResponseUtils {
    private ResponseUtils(){}
    public static <T> ResponseEntity<Object> get(int code,String message,T data){
        ResponseDto<T> responseDto = new ResponseDto<T>(code, message, data);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseDto,HttpStatus.OK);
        return responseEntity;
    }
    public static <T> ResponseEntity<Object> get(ErrorCode error){
        ResponseDto<T> responseDto = new ResponseDto<T>(error.getCode(), error.getMessage(), null);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseDto,HttpStatus.OK);
        return responseEntity;
    }
    
    
}

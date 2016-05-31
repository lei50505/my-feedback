package cn.rest.exception;

public class ErrorUtils {
    public static ServiceException get(ErrorCode ec){
        return new ServiceException(ec);
    }
    
}

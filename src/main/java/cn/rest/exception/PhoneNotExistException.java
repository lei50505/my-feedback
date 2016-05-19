package cn.rest.exception;


public class PhoneNotExistException extends ServiceException {

    private static final long serialVersionUID = 1L;

    
    public PhoneNotExistException(){
        super(ErrorCode.PhoneNotExistError);
    }



}

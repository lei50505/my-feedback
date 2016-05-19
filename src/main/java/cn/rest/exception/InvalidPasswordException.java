package cn.rest.exception;

public class InvalidPasswordException extends ServiceException {

    private static final long serialVersionUID = 1L;
    
    public InvalidPasswordException(){
        super(ErrorCode.InvalidPasswordError);
        
    }

}

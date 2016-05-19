package cn.rest.exception;

public class InvalidSignException extends ServiceException {

    public InvalidSignException() {
        super(ErrorCode.InvalidSignError);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}

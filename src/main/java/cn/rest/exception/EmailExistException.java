package cn.rest.exception;

public class EmailExistException extends ServiceException {

    public EmailExistException() {
        super(ErrorCode.EmailExistError);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}

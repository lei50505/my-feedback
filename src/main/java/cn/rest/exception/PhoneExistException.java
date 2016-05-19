package cn.rest.exception;

public class PhoneExistException extends ServiceException {

    public PhoneExistException() {
        super(ErrorCode.PhoneExistError);
    }

    private static final long serialVersionUID = 1L;

}

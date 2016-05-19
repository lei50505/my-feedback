package cn.rest.exception;

public class UserExistException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public UserExistException() {
        super(ErrorCode.PhoneExistError);
    }


}

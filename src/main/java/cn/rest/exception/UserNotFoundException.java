package cn.rest.exception;

public class UserNotFoundException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super(ErrorCode.UserNotFoundError);
    }

}

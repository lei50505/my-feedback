package cn.rest.exception;

public class SignNotFoundException extends ServiceException {

    public SignNotFoundException() {
        super(ErrorCode.SignNotFoundError);
    }

    private static final long serialVersionUID = 1L;

}

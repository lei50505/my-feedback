package cn.rest.exception;

public class SystemException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public SystemException(Throwable e) {
        super(ErrorCode.SystemError, e);
    }

}

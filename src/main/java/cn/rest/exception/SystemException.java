package cn.rest.exception;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SystemException(Throwable e) {
        super(e);
    }


}
package cn.rest.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;

    public ServiceException(ErrorCode ec) {
        super();
        this.code = ec.getCode();
        this.message = ec.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}

package cn.rest.exception;

public class ParamFormatException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public ParamFormatException() {

        super(ErrorCode.ParamFormatError);
    }


}

package cn.rest.response;

public class ResponseDto<T> {
    private int code;
    private String message;
    private T data;

    public ResponseDto(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}

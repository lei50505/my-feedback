package cn.rest.exception;

public enum ErrorCode {
    
    PhoneNotExistError(40011,"电话号码不存在"),
    ParamFormatError(40013,"参数有误"),
    PhoneExistError(40014,"电话号码已存在"),
    InvalidPasswordError(40021,"密码错误"),
    UserNotFoundError(40022,"用户不存在"),
    UserExistError(40023,"用户已存在"),
    SignNotFoundError(40024,"没有获取验证码"),
    InvalidSignError(40024,"验证码错误"),
    SystemError(50001,"服务异常")
    ;
    private int code;
    private String message;
    private ErrorCode(int code,String message){
        this.code=code;
        this.message=message;
    }
    public int getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}

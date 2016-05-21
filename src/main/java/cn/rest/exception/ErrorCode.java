package cn.rest.exception;

public enum ErrorCode {
    
    PhoneNotExistError(40011,"电话号码不存在"),
    ParamNullError(40012,"参数不能为空"),
    ParamFormatError(40013,"参数有误"),
    PhoneExistError(40014,"手机号码已存在"),
    InvalidPasswordError(40021,"密码错误"),
    UserNotFoundError(40022,"用户不存在"),
    UserExistError(40023,"用户已存在"),
    SignNotFoundError(40024,"没有获取验证码"),
    InvalidSignError(40025,"验证码错误"),
    PhoneFormatError(40026,"电话号码格式有误"),
    EmailExistError(40031,"邮箱已存在"),
    EmailNotFoundError(40032,"邮箱不存在"),
    SystemError(50001,"服务异常"),
    UnknownError(50002,"服务异常")
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

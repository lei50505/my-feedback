package cn.rest.exception;

public enum ErrorCode {
    
    PhoneNotExistError(40011,"手机号码不存在"),
    ParamNullError(40012,"参数为空"),
    ParamFormatError(40013,"参数有误"),
    PhoneExistError(40014,"手机号码已存在"),
    FeedbackTypeNotExistError(40015,"反馈类型不存在"),
    InvalidPasswordError(40021,"密码错误"),
    UserNotFoundError(40022,"用户不存在"),
    UserExistError(40023,"用户已存在"),
    SignNotFoundError(40024,"没有获取验证码"),
    InvalidSignError(40025,"验证码错误"),
    MobilePhoneFormatError(40026,"手机号码格式错误"),
    InvalidUserTokenError(40027,"用户token无效"),
    UserTokenNotFoundError(40028,"用户token不存在"),
    EmailExistError(40031,"邮箱已存在"),
    EmailNotFoundError(40032,"邮箱不存在"),
    EmailFormatError(40033,"邮箱格式错误"),
    ShopNotFoundError(40034,"店铺不存在"),
    UserShopNotFoundError(40035,"用户没有加入店铺"),
    SystemError(50001,"服务异常"),
    UnknownError(50002,"未知异常"),
    NullError(50003,"异常为空")
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

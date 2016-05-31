package cn.rest.service.impl;

import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;

public class ParamUtils {
    public static void paramNotNull(Object o){
        if(o==null){
            throw ErrorUtils.get(ErrorCode.ParamNullError);
        }
    }
    public static void validMobilePhone(String mobilePhone){
        paramNotNull(mobilePhone);
        if (!mobilePhone.matches("\\d{11}")) {
            throw ErrorUtils.get(ErrorCode.PhoneFormatError);
        }
    }
    public static void validFeedbackType(Integer fb_feedback_type){
        paramNotNull(fb_feedback_type);
        if (fb_feedback_type<0||fb_feedback_type>2) {
            throw ErrorUtils.get(ErrorCode.FeedbackTypeNotExistError);
        }
    }
}

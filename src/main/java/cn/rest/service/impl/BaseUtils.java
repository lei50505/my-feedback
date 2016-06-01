package cn.rest.service.impl;

import java.util.Random;

import cn.rest.entity.Shop;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.util.Md5Utils;
import cn.rest.util.RedisUtils;

public class BaseUtils {
    public static int getSign() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }

    public static void paramNotNull(Object obj) {
        if (obj == null) {
            throw ErrorUtils.get(ErrorCode.ParamNullError);
        }
    }

    public static void validEmail(String email) {
        paramNotNull(email);
        if (!email.matches(".+@.+\\..+")) {
            throw ErrorUtils.get(ErrorCode.EmailFormatError);
        }
    }

    public static void validMobilePhone(String mobilePhone) {
        paramNotNull(mobilePhone);
        if (!mobilePhone.matches("\\d{11}")) {
            throw ErrorUtils.get(ErrorCode.MobilePhoneFormatError);
        }
    }

    public static void validFeedbackType(Integer fb_feedback_type) {
        paramNotNull(fb_feedback_type);
        if (fb_feedback_type < 0 || fb_feedback_type > 2) {
            throw ErrorUtils.get(ErrorCode.FeedbackTypeNotExistError);
        }
    }

    public static int setEmailSignToRedis(String email) {
        validEmail(email);
        int num = getSign();
        RedisUtils.setIntWithExprSs("fb_shop_email_sign_" + email, num, 60 * 5);
        return num;
    }

    public static Integer getEmailSignFromRedis(String email) {
        validEmail(email);
        Integer validSign = RedisUtils.getInt("fb_shop_email_sign_" + email);
        return validSign;
    }

    public static void delEmailSignFromRedis(String email) {
        validEmail(email);
        RedisUtils.del("fb_shop_email_sign_" + email);
    }

    public static String encodePsw(String psw) {
        paramNotNull(psw);
        return Md5Utils.strToStr3(psw);
    }

    public static void encodePsw(Shop shop) {
        String psw = shop.getFb_shop_password();
        String encodePsw = encodePsw(psw);
        shop.setFb_shop_password(encodePsw);
    }

    public static int setPhoneMsgSignToRedis(String phone) {
        validMobilePhone(phone);
        int sign = getSign();
        RedisUtils.setIntWithExprSs("fb_sign_" + phone, sign, 60 * 5);
        return sign;
    }

    public static void validEmailSign(String email, Integer inputSign) {
        paramNotNull(inputSign);
        Integer validSign = getEmailSignFromRedis(email);
        if (validSign == null) {
            throw ErrorUtils.get(ErrorCode.SignNotFoundError);
        }
        if (!validSign.equals(inputSign)) {
            throw ErrorUtils.get(ErrorCode.InvalidSignError);
        }
        delEmailSignFromRedis(email);
    }

    public static void validPhoneMsgSign(String phone, Integer inputSign) {
        paramNotNull(inputSign);
        Integer validSign = getPhoneMsgSignFromRedis(phone);
        if (validSign == null) {
            throw ErrorUtils.get(ErrorCode.SignNotFoundError);
        }
        if (!validSign.equals(inputSign)) {
            throw ErrorUtils.get(ErrorCode.InvalidSignError);
        }
        delPhoneMsgSignFromRedis(phone);
    }

    public static void delPhoneMsgSignFromRedis(String phone) {
        validMobilePhone(phone);
        RedisUtils.del("fb_sign_" + phone);
    }

    public static Integer getPhoneMsgSignFromRedis(String phone) {
        validMobilePhone(phone);
        return RedisUtils.getInt("fb_sign_" + phone);
    }

    public static void encodePsw(User user) {
        paramNotNull(user);
        String psw = user.getFb_user_password();
        String encodePsw = encodePsw(psw);
        user.setFb_user_password(encodePsw);
    }
}

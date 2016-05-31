package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.ShopService;
import cn.rest.service.UserService;
import cn.rest.util.Md5Utils;
import cn.rest.util.PhoneUtils;
import cn.rest.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopService shopService;

    public static void paramNotNull(Object o) {
        if (o == null) {
            throw ErrorUtils.get(ErrorCode.ParamNullError);
        }
    }

    public static void validParamPhone(String phone) {
        paramNotNull(phone);
        if (!phone.matches("\\d{11}")) {
            throw ErrorUtils.get(ErrorCode.PhoneFormatError);
        }
    }

    public void addUser(User user) {

        paramNotNull(user);
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        User validUser = getUserByPhone(user.getFb_user_phone());

        if (validUser != null) {
            throw ErrorUtils.get(ErrorCode.UserExistError);
        }
        encodePsw(user);
        userDao.insert(user);

    }

    public User getUserById(int userId) {
        User user = userDao.selectByPk(userId);
        return user;
    }

    public static String encodePsw(String psw) {
        paramNotNull(psw);
        return Md5Utils.strToStr3(psw);
    }

    public static void encodePsw(User user) {
        paramNotNull(user);
        String psw = user.getFb_user_password();
        String encodePsw = encodePsw(psw);
        user.setFb_user_password(encodePsw);
    }

    public static void setPhoneMsgSignToRedis(String phone, int sign) {
        validParamPhone(phone);
        RedisUtils.setIntWithExprSs("fb_sign_" + phone, sign, 60 * 5);
    }

    public static void delPhoneMsgSignFromRedis(String phone) {
        validParamPhone(phone);
        RedisUtils.del("fb_sign_" + phone);
    }

    public static Integer getPhoneMsgSignFromRedis(String phone) {
        validParamPhone(phone);
        return RedisUtils.getInt("fb_sign_" + phone);
    }

    public static int createPhoneMsgSign() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }

    @Override
    public int sendPhoneMsgSign(String phone) {
        User user = getUserByPhone(phone);
        if (user != null) {
            throw ErrorUtils.get(ErrorCode.PhoneExistError);
        }
        int num = createPhoneMsgSign();
        setPhoneMsgSignToRedis(phone, num);
        String msg = "在5分钟内有效，您的验证码为：" + num;
        PhoneUtils.sendMsg(phone, msg);
        return num;
    }

    @Override
    public boolean existPhone(String phone) {
        User user = getUserByPhone(phone);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public String userLogin(String phone, String psw) {
        User validUser = getUserByPhone(phone);
        if (validUser == null) {
            throw ErrorUtils.get(ErrorCode.UserNotFoundError);
        }
        String inputPsw = encodePsw(psw);
        String validPsw = validUser.getFb_user_password();
        if (!validPsw.equals(inputPsw)) {
            throw ErrorUtils.get(ErrorCode.InvalidPasswordError);
        }
        String token = UUID.randomUUID().toString();

        Timestamp expiredTime = new Timestamp(System.currentTimeMillis() + 1000
                * 60 * 60 * 24 * 30L);
        validUser.setFb_user_token(token);
        validUser.setFb_expired_at(expiredTime);
        updateUserByPk(validUser);
        return token;
    }

    public User getUserByToken(String token) {
        paramNotNull(token);
        User user = userDao.getByToken(token);
        if (user == null) {
            throw ErrorUtils.get(ErrorCode.UserTokenNotFoundError);
        }
        if (user.getFb_expired_at().getTime() < new Date().getTime()) {
            user.setFb_expired_at(null);
            user.setFb_user_token(null);
            updateUserByPk(user);
            throw ErrorUtils.get(ErrorCode.InvalidUserTokenError);
        }
        return user;
    }

    public User getUserByPhone(String phone) {
        validParamPhone(phone);
        User user = userDao.getByPhone(phone);
        return user;
    }

    public void updateUserByPk(User user) {
        paramNotNull(user);
        paramNotNull(user.getFb_user_id());
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        paramNotNull(user.getFb_user_phone());
        userDao.updateByPk(user);
    }

    @Override
    public void addUserBySign(User user, Integer sign) {
        paramNotNull(sign);
        paramNotNull(user);
        String phone = user.getFb_user_phone();
        Integer validSign = getPhoneMsgSignFromRedis(phone);
        if (validSign == null) {
            throw ErrorUtils.get(ErrorCode.SignNotFoundError);
        }
        if (!validSign.equals(sign)) {
            throw ErrorUtils.get(ErrorCode.InvalidSignError);
        }
        delPhoneMsgSignFromRedis(phone);
        addUser(user);
    }

}
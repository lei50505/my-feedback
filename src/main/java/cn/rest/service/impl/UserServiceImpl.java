package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.dto.UserInfo;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.exception.ServiceException;
import cn.rest.service.UserService;
import cn.rest.util.Md5Utils;
import cn.rest.util.PhoneUtils;
import cn.rest.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public static void paramNotNull(Object o) throws ServiceException {
        if (o == null) {
            throw ErrorUtils.get(ErrorCode.ParamNullError);
        }
    }

    public static void validParamPhone(String phone) throws ServiceException {
        paramNotNull(phone);
        if (!phone.matches("\\d{11}")) {
            throw ErrorUtils.get(ErrorCode.PhoneFormatError);
        }
    }

    public void addUser(User user) throws ServiceException {

        paramNotNull(user);
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        User validUser = getUserByPhone(user.getFb_user_phone());

        if (validUser != null) {
            throw ErrorUtils.get(ErrorCode.UserExistError);
        }
        encodePsw(user);
        try {
            userDao.insert(user);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }

    }

    public User getUserByPk(int userId) throws ServiceException {
        User user = null;
        try {
            user = userDao.selectByPk(userId);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
        return user;
    }

    public static String encodePsw(String psw) throws ServiceException {
        paramNotNull(psw);
        return Md5Utils.strToStr3(psw);
    }

    public static void encodePsw(User user) throws ServiceException {
        paramNotNull(user);
        String psw = user.getFb_user_password();
        String encodePsw = encodePsw(psw);
        user.setFb_user_password(encodePsw);
    }

    public static void setPhoneMsgSignToRedis(String phone, int sign)
            throws ServiceException {
        validParamPhone(phone);
        try {
            RedisUtils.setIntWithExprSs("fb_sign_" + phone, sign, 60 * 5);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }
    public static void delPhoneMsgSignFromRedis(String phone)
            throws ServiceException {
        validParamPhone(phone);
        try {
            RedisUtils.del("fb_sign_" + phone);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    public static Integer getPhoneMsgSignFromRedis(String phone)
            throws ServiceException {
        validParamPhone(phone);
        try {
            return RedisUtils.getInt("fb_sign_" + phone);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    public static int createPhoneMsgSign() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }

    @Override
    public int sendPhoneMsgSign(String phone) throws ServiceException {
        User user = getUserByPhone(phone);
        if (user != null) {
            throw ErrorUtils.get(ErrorCode.PhoneExistError);
        }
        int num = createPhoneMsgSign();
        setPhoneMsgSignToRedis(phone, num);
        String msg = "在5分钟内有效，您的验证码为：" + num;
        try {
            PhoneUtils.sendMsg(phone, msg);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
        return num;
    }

    @Override
    public boolean existPhone(String phone) throws ServiceException {
        User user = getUserByPhone(phone);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public String userLogin(String phone, String psw) throws ServiceException {
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

    public User getUserByToken(String token) throws ServiceException {
        paramNotNull(token);
        User user = null;
        try {
            user = userDao.getByToken(token);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
        if(user==null){
            return null;
        }
        if (user.getFb_expired_at().getTime() < new Date().getTime()) {
            user.setFb_expired_at(null);
            user.setFb_user_token(null);
            updateUserByPk(user);
            return null;
        }
        return user;
    }

    public User getUserByPhone(String phone) throws ServiceException {
        validParamPhone(phone);
        User user = null;
        try {
            user = userDao.getByPhone(phone);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
        return user;
    }

    public void updateUserByPk(User user) throws ServiceException {
        paramNotNull(user);
        paramNotNull(user.getFb_user_id());
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        paramNotNull(user.getFb_user_phone());
        try {
            userDao.updateByPk(user);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    @Override
    public void addUserBySign(User user, Integer sign) throws ServiceException {
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

    @Override
    public UserInfo getUserInfoByToken(String token)
            throws ServiceException {
        User user = getUserByToken(token);
        if(user==null){
            throw ErrorUtils.get(ErrorCode.InvalidUserTokenError);
        }
        UserInfo u = new UserInfo();
        u.setFb_user_id(user.getFb_user_id());
        u.setFb_shop_id(user.getFb_shop_id());
        u.setFb_user_name(user.getFb_user_name());
        u.setFb_user_phone(user.getFb_user_phone());
        u.setFb_user_status(user.getFb_user_status());
        return u;
    }

}
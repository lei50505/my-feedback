package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.PhoneExistException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;
import cn.rest.exception.UserExistException;
import cn.rest.exception.UserNotFoundException;
import cn.rest.service.UserService;
import cn.rest.util.Md5Utils;
import cn.rest.util.PhoneUtils;
import cn.rest.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) throws SystemException,
            ParamFormatException, UserExistException {

        if (user == null) {
            throw new ParamFormatException();
        }
        if (user.getFb_user_name() == null) {
            throw new ParamFormatException();
        }
        if (user.getFb_user_password() == null) {
            throw new ParamFormatException();
        }
        if (user.getFb_user_phone() == null) {
            throw new ParamFormatException();
        }
        User validUser = getUserByPhone(user.getFb_user_phone());

        if (validUser != null) {
            throw new UserExistException();
        }
        encodePsw(user);
        try {
            userDao.insert(user);
        } catch (Exception e) {
            throw new SystemException(e);
        }

    }

    public User getUserByPk(int userId) throws SystemException {
        User user = null;
        try {
            user = userDao.selectByPk(userId);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return user;
    }

    public static String encodePsw(String psw) {
        return Md5Utils.strToStr3(psw);
    }

    public static void encodePsw(User user) {
        String psw = user.getFb_user_password();
        String encodePsw = encodePsw(psw);
        user.setFb_user_password(encodePsw);
    }

    @Override
    public int getMsgSign(String phone) throws SystemException,
            ParamFormatException, PhoneExistException {
        User user = getUserByPhone(phone);
        if (user != null) {
            throw new PhoneExistException();
        }
        Random random = new Random();
        int num = random.nextInt(899999) + 100000;
        try {
            RedisUtils.setIntWithExprSs("fb_sign_" + phone, num, 60 * 5);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        String msg = "在5分钟内有效，您的验证码为：" + num;
        try {
            PhoneUtils.sendMsg(phone, msg);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return num;
    }

    @Override
    public boolean existPhone(String phone) throws ParamFormatException,
            SystemException {
        User user = getUserByPhone(phone);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public String userLogin(String phone, String psw)
            throws UserNotFoundException, InvalidPasswordException,
            ParamFormatException, SystemException {
        User validUser = getUserByPhone(phone);
        if (validUser == null) {
            throw new UserNotFoundException();
        }
        String inputPsw = encodePsw(psw);
        String validPsw = validUser.getFb_user_password();
        if (!validPsw.equals(inputPsw)) {
            throw new InvalidPasswordException();
        }
        String token = UUID.randomUUID().toString();

        Timestamp expiredTime = new Timestamp(System.currentTimeMillis() + 1000
                * 60 * 60 * 24 * 30L);
        validUser.setFb_user_token(token);
        validUser.setFb_expired_at(expiredTime);
        updateUserByPk(validUser);
        return token;
    }

    public User getUserByToken(String token) throws ParamFormatException,
            SystemException {
        if (token == null) {
            throw new ParamFormatException();
        }
        User user = null;
        try {
            user = userDao.getByToken(token);
        } catch (Exception e) {
            throw new SystemException(e);
        }

        if (user.getFb_expired_at().getTime() < new Date().getTime()) {
            user.setFb_expired_at(null);
            user.setFb_user_token(null);
            updateUserByPk(user);
            return null;
        }
        return user;
    }

    public User getUserByPhone(String phone) throws ParamFormatException,
            SystemException {
        String phoneReg = "\\d{11}";
        if (phone == null) {
            throw new ParamFormatException();
        }
        if (!phone.matches(phoneReg)) {
            throw new ParamFormatException();
        }
        User user = null;
        try {
            user = userDao.getByPhone(phone);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return user;
    }

    public void updateUserByPk(User user) throws ParamFormatException,
            SystemException {
        if (user == null) {
            throw new ParamFormatException();
        }
        if (user.getFb_user_id() == null) {
            throw new ParamFormatException();
        }
        try {
            userDao.updateByPk(user);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @Override
    public void addUserBySign(User user, Integer sign)
            throws ParamFormatException, SystemException,
            SignNotFoundException, InvalidSignException, UserExistException {
        if (sign == null) {
            throw new ParamFormatException();
        }
        if (user == null) {
            throw new ParamFormatException();
        }
        String phone = user.getFb_user_phone();
        if (phone == null) {
            throw new ParamFormatException();
        }
        Integer validSign = null;

        try {
            validSign = RedisUtils.getInt("fb_sign_" + phone);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        if (validSign == null) {
            throw new SignNotFoundException();
        }

        if (!validSign.equals(sign)) {
            throw new InvalidSignException();
        }
        try {
            RedisUtils.del("fb_sign_" + phone);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        addUser(user);

    }

}
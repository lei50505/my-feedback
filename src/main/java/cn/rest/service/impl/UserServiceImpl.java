package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.UserNotFoundException;
import cn.rest.service.UserService;
import cn.rest.util.Md5Utils;
import cn.rest.util.PhoneUtils;
import cn.rest.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public int add(User user) {
        encodePsw(user);
        try {
            userDao.insert(user);
        } catch (DuplicateKeyException e) {
            log.warn("插入数据时，违背唯一性约束", e);
            return 1;
        }
        return 0;
    }

    @Override
    public User getByPk(int userId) {
        return userDao.selectByPk(userId);
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
    public int getSign(String phone) {
        Random random = new Random();
        int num = random.nextInt(899999) + 100000;
        try {
            RedisUtils.setIntWithExprSs("fb_sign_" + phone, num, 60 * 5);
        } catch (Exception e) {
            throw e;
        }
        String msg = "在5分钟内有效，您的验证码为：" + num;
        PhoneUtils.sendMsg(phone, msg);
        return num;
    }

    @Override
    public boolean existPhone(String phone) {
        User user = userDao.getByPhone(phone);
        return user != null;
    }

    @Override
    public String login(String phone, String psw) throws UserNotFoundException,
            InvalidPasswordException {
        User validUser = userDao.getByPhone(phone);
        if (validUser == null) {
            throw new UserNotFoundException("用户不存在");
        }
        String inputPsw = encodePsw(psw);
        String validPsw = validUser.getFb_user_password();
        if (!validPsw.equals(inputPsw)) {
            throw new InvalidPasswordException("密码错误");
        }
        String token = UUID.randomUUID().toString();

        Timestamp expiredTime = new Timestamp(System.currentTimeMillis() + 1000 * 60
                * 60 * 24 * 30L);
        User updateUser = new User();
        updateUser.setFb_user_id(validUser.getFb_user_id());
        updateUser.setFb_expired_at(expiredTime);
        updateUser.setFb_user_token(token);
        userDao.updateByPkSelective(updateUser);
        return token;
    }

    @Override
    public boolean validToken(String token) {
        User user = userDao.getByToken(token);
        if(user==null){
            return false;
        }
        if(user.getFb_expired_at().getTime()<new Date().getTime()){
            user.setFb_expired_at(null);
            user.setFb_user_token(null);
            userDao.updateByPk(user);
            return false;
        }
        return true;
    }


    @Override
    public void update(User user) {
        userDao.updateByPk(user);
    }

}
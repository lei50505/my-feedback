package cn.rest.service.impl;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
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
            log.warn("插入数据时，违背唯一性约束",e);
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

}
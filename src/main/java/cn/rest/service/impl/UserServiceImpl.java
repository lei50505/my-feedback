package cn.rest.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
import cn.rest.service.UserService;
import cn.rest.util.Md5Utils;
import cn.rest.util.PhoneUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {
        encodePsw(user);
        userDao.insert(user);
    }

    @Override
    public User getByPk(int userId) {
        return userDao.selectByPk(userId);
    }
    
    public static String encodePsw(String psw){
        return Md5Utils.strToStr3(psw);
    }
    public static void encodePsw(User user){
        String psw = user.getFb_user_password();
        String encodePsw = encodePsw(psw);
        user.setFb_user_password(encodePsw);
    }

    @Override
    public int getSign(String phone) {
        Random random = new Random();
        int num = random.nextInt(899999)+100000;
        String msg = "您的验证码："+num;
        PhoneUtils.sendMsg(phone, msg);
        return num;
    }

}
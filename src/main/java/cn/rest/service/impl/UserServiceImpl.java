package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.Feedback;
import cn.rest.entity.Shop;
import cn.rest.entity.User;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.ShopService;
import cn.rest.service.UserService;
import cn.rest.util.PhoneUtils;
import static cn.rest.service.impl.BaseUtils.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopService shopService;

    @Override
    public void add(User user) {
        paramNotNull(user);
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        User validUser = getByPhone(user.getFb_user_phone());
        if (validUser != null) {
            throw ErrorUtils.get(ErrorCode.UserExistError);
        }
        encodePsw(user);
        userDao.add(user);
    }

    @Override
    public User getById(Integer fb_user_id) {
        paramNotNull(fb_user_id);
        return userDao.getById(fb_user_id);
    }

    @Override
    public int sendPhoneMsgSign(String phone) {
        User user = getByPhone(phone);
        if (user != null) {
            throw ErrorUtils.get(ErrorCode.PhoneExistError);
        }
        int num = setPhoneMsgSignToRedis(phone);
        String msg = "在5分钟内有效，您的验证码为：" + num;
        PhoneUtils.sendMsg(phone, msg);
        return num;
    }

    @Override
    public boolean existPhone(String phone) {
        User user = getByPhone(phone);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public String userLogin(String phone, String psw) {
        User validUser = getByPhone(phone);
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
        updateById(validUser);
        return token;
    }

    @Override
    public User getByToken(String fb_user_token) {
        paramNotNull(fb_user_token);
        return userDao.getByToken(fb_user_token);
    }

    @Override
    public User getByPhone(String fb_user_phone) {
        validMobilePhone(fb_user_phone);
        return userDao.getByPhone(fb_user_phone);
    }

    @Override
    public void updateById(User user) {
        paramNotNull(user);
        paramNotNull(user.getFb_user_id());
        paramNotNull(user.getFb_user_name());
        paramNotNull(user.getFb_user_password());
        paramNotNull(user.getFb_user_phone());
        userDao.updateById(user);
    }

    @Override
    public void addUserBySign(User user, Integer sign) {
        paramNotNull(user);
        validPhoneMsgSign(user.getFb_user_phone(), sign);
        add(user);
    }

    @Override
    public void updateByIdSelective(User user) {
        paramNotNull(user);
        paramNotNull(user.getFb_user_id());
        userDao.updateByIdSelective(user);
    }

    @Override
    public User getValidUserByToken(String token) {
        User user = getByToken(token);
        if (user == null) {
            throw ErrorUtils.get(ErrorCode.UserTokenNotFoundError);
        }
        if (user.getFb_expired_at().getTime() < new Date().getTime()) {
            user.setFb_expired_at(null);
            user.setFb_user_token(null);
            updateById(user);
            throw ErrorUtils.get(ErrorCode.InvalidUserTokenError);
        }
        return user;
    }

    @Override
    public Shop getShopByUserToken(String fb_user_token) {
        User user = getValidUserByToken(fb_user_token);
        Integer fb_shop_id = user.getFb_shop_id();
        if(fb_shop_id==null){
            throw ErrorUtils.get(ErrorCode.UserShopNotFoundError);
        }
        return shopService.getById(fb_shop_id);
    }

    @Override
    public List<Feedback> getPageByUserToken(String fb_user_token, int page) {
        return null;
    }

}
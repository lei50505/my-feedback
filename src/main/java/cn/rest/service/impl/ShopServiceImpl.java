package cn.rest.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopDao;
import cn.rest.entity.Shop;
import cn.rest.exception.EmailExistException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;
import cn.rest.service.ShopService;
import cn.rest.util.EmailUtils;
import cn.rest.util.Md5Utils;
import cn.rest.util.RedisUtils;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    public Shop getShopByEmail(String email) throws SystemException,
            ParamFormatException {
        if (email == null) {
            throw new ParamFormatException();
        }
        if (!email.matches(".+@.+\\..+")) {
            throw new ParamFormatException();
        }
        Shop shop = null;
        try {
            shop = shopDao.getByEmail(email);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return shop;
    }

    @Override
    public int sendEmailSign(String email) throws ParamFormatException,
            SystemException, EmailExistException {

        Shop shop = getShopByEmail(email);
        if (shop != null) {
            throw new EmailExistException();
        }
        Random random = new Random();
        int num = random.nextInt(899999) + 100000;
        try {
            RedisUtils.setIntWithExprSs("fb_shop_email_sign_" + email, num,
                    60 * 5);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        String msg = "在5分钟内有效，您的验证码为：" + num;
        try {
            EmailUtils.send(email, msg);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        return num;
    }

    public static String encodePsw(String psw) {
        return Md5Utils.strToStr3(psw);
    }

    public static void encodePsw(Shop shop) {
        String psw = shop.getFb_shop_password();
        String encodePsw = encodePsw(psw);
        shop.setFb_shop_password(encodePsw);
    }

    @Override
    public void addShop(Shop shop, Integer sign) throws ParamFormatException,
            SystemException, SignNotFoundException, InvalidSignException,
            EmailExistException {
        if (sign == null) {
            throw new ParamFormatException();
        }
        if (shop == null) {
            throw new ParamFormatException();
        }
        String email = shop.getFb_shop_email();
        if (email == null) {
            throw new ParamFormatException();
        }
        if (!email.matches(".+@.+\\..+")) {
            throw new ParamFormatException();
        }
        Integer validSign = null;
        try {
            validSign = RedisUtils.getInt("fb_shop_email_sign_" + email);
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
            RedisUtils.del("fb_shop_email_sign_" + email);
        } catch (Exception e) {
            throw new SystemException(e);
        }
        Shop validShop = null;
        validShop = getShopByEmail(email);
        if (validShop != null) {
            throw new EmailExistException();
        }
        encodePsw(shop);
        try {
            shopDao.insert(shop);
        } catch (Exception e) {
            throw new SystemException(e);
        }

    }

}

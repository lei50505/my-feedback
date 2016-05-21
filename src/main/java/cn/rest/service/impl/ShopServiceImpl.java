package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopDao;
import cn.rest.entity.Shop;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.exception.ServiceException;
import cn.rest.service.ShopService;
import cn.rest.util.EmailUtils;
import cn.rest.util.Md5Utils;
import cn.rest.util.RedisUtils;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    public static void paramNotNull(Object o) throws ServiceException {
        if (o == null) {
            throw ErrorUtils.get(ErrorCode.ParamFormatError);
        }
    }

    public static void validParamEmail(String email) throws ServiceException {
        paramNotNull(email);
        if (!email.matches(".+@.+\\..+")) {
            throw ErrorUtils.get(ErrorCode.ParamFormatError);
        }
    }

    public Shop getShopByEmail(String email) throws ServiceException {
        validParamEmail(email);
        Shop shop = null;
        try {
            shop = shopDao.getByEmail(email);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
        return shop;
    }

    public static void setEmailSignToRedis(String email, int num)
            throws ServiceException {
        validParamEmail(email);
        try {
            RedisUtils.setIntWithExprSs("fb_shop_email_sign_" + email, num,
                    60 * 5);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    public static int getInitSign() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }

    public static void sendEmail(String email, String msg)
            throws ServiceException {
        try {
            EmailUtils.send(email, msg);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    @Override
    public int sendEmailSign(String email) throws ServiceException {

        Shop shop = getShopByEmail(email);
        if (shop != null) {
            throw ErrorUtils.get(ErrorCode.EmailExistError);
        }
        int num = getInitSign();
        setEmailSignToRedis(email, num);
        String msg = "在5分钟内有效，您的验证码为：" + num;
        sendEmail(email, msg);
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

    public static int getEmailSignFromRedis(String email)
            throws ServiceException {
        validParamEmail(email);
        Integer validSign = null;
        try {
            validSign = RedisUtils.getInt("fb_shop_email_sign_" + email);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }

        return validSign;
    }

    public static void delEmailSignFromRedis(String email)
            throws ServiceException {
        validParamEmail(email);
        try {
            RedisUtils.del("fb_shop_email_sign_" + email);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    public static void varifySignSuccess(String email, Integer inputSign)
            throws ServiceException {
        Integer validSign = getEmailSignFromRedis(email);
        if (validSign == null) {
            throw ErrorUtils.get(ErrorCode.SignNotFoundError);
        }
        paramNotNull(inputSign);
        if (!validSign.equals(inputSign)) {
            throw ErrorUtils.get(ErrorCode.InvalidSignError);
        }
        delEmailSignFromRedis(email);
    }

    public void insertShop(Shop shop) throws ServiceException {
        paramNotNull(shop);
        Shop validShop = getShopByEmail(shop.getFb_shop_email());
        if (validShop != null) {
            throw ErrorUtils.get(ErrorCode.EmailExistError);
        }
        encodePsw(shop);
        try {
            shopDao.insert(shop);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

    @Override
    public void addShop(Shop shop, Integer sign) throws ServiceException {
        paramNotNull(shop);
        String email = shop.getFb_shop_email();
        Integer validSign = getEmailSignFromRedis(email);
        if (validSign == null) {
            throw ErrorUtils.get(ErrorCode.SignNotFoundError);
        }
        paramNotNull(sign);
        if (!validSign.equals(sign)) {
            throw ErrorUtils.get(ErrorCode.InvalidSignError);
        }
        delEmailSignFromRedis(email);
        insertShop(shop);
    }

    @Override
    public String login(String email, String psw) throws ServiceException {
        Shop shop = getShopByEmail(email);
        if (shop == null) {
            throw ErrorUtils.get(ErrorCode.EmailNotFoundError);
        }
        String encodePsw = encodePsw(psw);
        if (!encodePsw.equals(shop.getFb_shop_password())) {
            throw ErrorUtils.get(ErrorCode.InvalidPasswordError);
        }
        String token = UUID.randomUUID().toString();
        shop.setFb_shop_token(token);
        shop.setFb_expired_at(new Timestamp(System.currentTimeMillis() + 1000
                * 60 * 60 * 24 * 30L));
        updateById(shop);
        return token;
    }

    public void updateById(Shop shop) throws ServiceException {
        paramNotNull(shop);
        paramNotNull(shop.getFb_shop_id());
        paramNotNull(shop.getFb_shop_name());
        paramNotNull(shop.getFb_shop_phone());
        paramNotNull(shop.getFb_shop_address());
        paramNotNull(shop.getFb_shop_email());
        paramNotNull(shop.getFb_shop_password());
        try {
            shopDao.updateById(shop);
        } catch (Exception e) {
            throw ErrorUtils.get(e);
        }
    }

}

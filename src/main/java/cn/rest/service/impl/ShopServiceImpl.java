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
import cn.rest.service.ShopService;
import cn.rest.util.EmailUtils;
import cn.rest.util.Md5Utils;
import cn.rest.util.RedisUtils;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    public static void paramNotNull(Object o) {
        if (o == null) {
            throw ErrorUtils.get(ErrorCode.ParamFormatError);
        }
    }

    public static void validParamEmail(String email) {
        paramNotNull(email);
        if (!email.matches(".+@.+\\..+")) {
            throw ErrorUtils.get(ErrorCode.ParamFormatError);
        }
    }

    public Shop getShopByEmail(String email) {
        validParamEmail(email);
        Shop shop = shopDao.getByEmail(email);
        return shop;
    }

    public static void setEmailSignToRedis(String email, int num) {
        validParamEmail(email);
        RedisUtils.setIntWithExprSs("fb_shop_email_sign_" + email, num, 60 * 5);
    }

    public static int getInitSign() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }

    public static void sendEmail(String email, String msg) {
        EmailUtils.send(email, msg);
    }

    @Override
    public int sendEmailSign(String email) {

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

    public static int getEmailSignFromRedis(String email) {
        validParamEmail(email);
        Integer validSign = RedisUtils.getInt("fb_shop_email_sign_" + email);

        return validSign;
    }

    public static void delEmailSignFromRedis(String email) {
        validParamEmail(email);
        RedisUtils.del("fb_shop_email_sign_" + email);
    }

    public static void varifySignSuccess(String email, Integer inputSign) {
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

    public void insertShop(Shop shop) {
        paramNotNull(shop);
        Shop validShop = getShopByEmail(shop.getFb_shop_email());
        if (validShop != null) {
            throw ErrorUtils.get(ErrorCode.EmailExistError);
        }
        encodePsw(shop);
        shopDao.insert(shop);
    }

    @Override
    public void addShop(Shop shop, Integer sign) {
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
    public String login(String email, String psw) {
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

    public void updateById(Shop shop) {
        paramNotNull(shop);
        paramNotNull(shop.getFb_shop_id());
        paramNotNull(shop.getFb_shop_name());
        paramNotNull(shop.getFb_shop_phone());
        paramNotNull(shop.getFb_shop_address());
        paramNotNull(shop.getFb_shop_email());
        paramNotNull(shop.getFb_shop_password());
        shopDao.updateById(shop);
    }

    @Override
    public Shop getShopById(int shopId) {
        return shopDao.selectByPk(shopId);
    }

}

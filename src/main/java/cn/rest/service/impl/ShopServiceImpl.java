package cn.rest.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.ShopDao;
import cn.rest.entity.Shop;
import cn.rest.exception.ErrorCode;
import cn.rest.exception.ErrorUtils;
import cn.rest.service.ShopService;
import cn.rest.util.EmailUtils;
import static cn.rest.service.impl.BaseUtils.*;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop getByEmail(String email) {
        validEmail(email);
        Shop shop = shopDao.getByEmail(email);
        return shop;
    }

    @Override
    public int sendEmailSign(String email) {

        Shop shop = getByEmail(email);
        if (shop != null) {
            throw ErrorUtils.get(ErrorCode.EmailExistError);
        }
        int num = setEmailSignToRedis(email);
        String msg = "在5分钟内有效，您的验证码为：" + num;
        EmailUtils.send(email, msg);
        return num;
    }

    @Override
    public void add(Shop shop) {
        paramNotNull(shop);
        Shop validShop = getByEmail(shop.getFb_shop_email());
        if (validShop != null) {
            throw ErrorUtils.get(ErrorCode.EmailExistError);
        }
        encodePsw(shop);
        shopDao.add(shop);
    }

    @Override
    public void addShopWithSign(Shop shop, Integer sign) {
        paramNotNull(shop);
        validEmailSign(shop.getFb_shop_email(), sign);
        add(shop);
    }

    @Override
    public String login(String email, String psw) {
        Shop shop = getByEmail(email);
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

    @Override
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
    public Shop getById(Integer fb_shop_id) {
        paramNotNull(fb_shop_id);
        return shopDao.getById(fb_shop_id);
    }

    @Override
    public void updateByIdSelective(Shop shop) {
        paramNotNull(shop);
        paramNotNull(shop.getFb_shop_id());
        shopDao.updateByIdSelective(shop);
    }

    @Override
    public Shop getByToken(String fb_shop_token) {
        paramNotNull(fb_shop_token);
        return shopDao.getByToken(fb_shop_token);
    }

}

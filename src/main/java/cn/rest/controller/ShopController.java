package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.Shop;
import cn.rest.exception.ServiceException;
import cn.rest.response.ResponseUtils;
import cn.rest.service.ShopService;

@RequestMapping("/shop")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/email/sign", method = RequestMethod.POST)
    public ResponseEntity<Object> sendEmailSign(String fb_shop_email) {
        int sign = 0;
        try {
            sign = shopService.sendEmailSign(fb_shop_email);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get(sign);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addShop(Shop shop, Integer fb_shop_sign) {

        try {
            shopService.addShop(shop, fb_shop_sign);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(String fb_shop_email,
            String fb_shop_password) {
        String token = null;
        try {
            token = shopService.login(fb_shop_email, fb_shop_password);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get(token);
    }
}

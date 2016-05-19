package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.Shop;
import cn.rest.exception.EmailExistException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;
import cn.rest.service.ShopService;
import cn.rest.util.ResponseUtils;

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
        } catch (ParamFormatException | SystemException | EmailExistException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, sign);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addShop(Shop shop, Integer fb_shop_sign) {

        try {
            shopService.addShop(shop, fb_shop_sign);
        } catch (ParamFormatException | SystemException | SignNotFoundException
                | InvalidSignException | EmailExistException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, null);
    }
}

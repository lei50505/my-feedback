package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.Shop;
import cn.rest.entity.User;
import cn.rest.exception.ServiceException;
import cn.rest.response.ResponseUtils;
import cn.rest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Object> addUser(User user, Integer fb_sign)
            throws ServiceException {

        userService.addUserBySign(user, fb_sign);
        return ResponseUtils.get();
    }

    @RequestMapping(value = "/sign/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMsgSign(@PathVariable String phone)
            throws ServiceException {
        int sign = userService.sendPhoneMsgSign(phone);
        return ResponseUtils.get(sign);
    }

    @RequestMapping(value = "/phone/exist/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> existPhone(@PathVariable String phone)
            throws ServiceException {
        boolean flag = userService.existPhone(phone);
        return ResponseUtils.get(flag);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(String fb_user_phone,
            String fb_user_password) throws ServiceException {
        String token = userService.userLogin(fb_user_phone, fb_user_password);
        return ResponseUtils.get(token);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getUserInfo(String fb_user_token)
            throws ServiceException {
        User user = userService.getByToken(fb_user_token);
        return ResponseUtils.get(user);
    }

    @RequestMapping(value = "/get-shop-by-user-token", method = RequestMethod.POST)
    public ResponseEntity<Object> getShopByUserToken(String fb_user_token) {
        Shop shop = userService.getShopByUserToken(fb_user_token);
        return ResponseUtils.get(shop);
        
    }

}

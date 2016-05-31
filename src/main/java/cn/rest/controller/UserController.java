package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> addUser(User user, Integer fb_sign) {

        try {
            userService.addUserBySign(user, fb_sign);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get();
    }

    @RequestMapping(value = "/sign/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMsgSign(@PathVariable String phone) {
        int sign = 0;
        try {
            sign = userService.sendPhoneMsgSign(phone);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get(sign);
    }

    @RequestMapping(value = "/phone/exist/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> existPhone(@PathVariable String phone) {
        boolean flag = true;
        try {
            flag = userService.existPhone(phone);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get(flag);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(String fb_user_phone,
            String fb_user_password) {
        String token = null;
        try {
            token = userService.userLogin(fb_user_phone, fb_user_password);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
        return ResponseUtils.get(token);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<Object> getUserInfo(String fb_user_token) {
        try {
            User user = userService.getUserByToken(fb_user_token);
            return ResponseUtils.get(user);
        } catch (ServiceException e) {
            return ResponseUtils.get(e);
        }
    }

}

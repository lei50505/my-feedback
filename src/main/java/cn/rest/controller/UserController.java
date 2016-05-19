package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.PhoneExistException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;
import cn.rest.exception.UserExistException;
import cn.rest.exception.UserNotFoundException;
import cn.rest.service.UserService;
import cn.rest.util.ResponseUtils;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Object> addUser(User user, Integer fb_sign) {

        try {
            userService.addUserBySign(user, fb_sign);
        } catch (ParamFormatException | UserExistException | SystemException
                | SignNotFoundException | InvalidSignException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, null);
    }

    @RequestMapping(value = "/sign/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMsgSign(@PathVariable String phone) {
        int sign=0;
        try {
            sign = userService.getMsgSign(phone);
        } catch (SystemException | ParamFormatException | PhoneExistException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, sign);
    }

    @RequestMapping(value = "/phone/exist/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> existPhone(@PathVariable String phone) {
        boolean flag = true;
        try {
            flag = userService.existPhone(phone);
        } catch (ParamFormatException | SystemException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, flag);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(String fb_user_phone,
            String fb_user_password) {
        String token = null;
        try {
            token = userService.userLogin(fb_user_phone, fb_user_password);
        } catch (UserNotFoundException | InvalidPasswordException
                | ParamFormatException | SystemException e) {
            return ResponseUtils.get(e.getCode(), e.getMessage(), null);
        }
        return ResponseUtils.get(20000, null, token);
    }

}

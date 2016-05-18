package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.UserNotFoundException;
import cn.rest.service.UserService;
import cn.rest.util.ConfigUtils;
import cn.rest.util.RedisUtils;
import cn.rest.util.ResponseUtils;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{fb_user_id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable int fb_user_id) {
        User user = userService.getByPk(fb_user_id);
        return ResponseUtils.get(20000, null, user);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Object> add(User user, Integer fb_sign) {
        Integer validSign;

        String key = "fb_sign_" + user.getFb_user_phone();
        try {
            validSign = RedisUtils.getInt(key);
        } catch (Exception e) {
            return ResponseUtils.get(40002, "获取验证码失败", null);
        }
        if (validSign == null) {
            return ResponseUtils.get(40003, "请获取验证码", null);
        }
        if (!validSign.equals(fb_sign)) {
            return ResponseUtils.get(40001, "验证码不正确", null);
        }
        RedisUtils.del(key);

        if (userService.add(user) != 0) {
            return ResponseUtils.get(40004, "注册用户失败", null);
        }
        User newUser = userService.getByPk(user.getFb_user_id());
        return ResponseUtils.get(20000, null, newUser);
    }

    @RequestMapping(value = "/sign/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> getSign(@PathVariable String phone) {
        int sign = 0;
        try {
            sign = userService.getSign(phone);
        } catch (Exception e) {
            return ResponseUtils.get(40001, "发送验证码失败", null);
        }
        if (ConfigUtils.getInt("conf.msg.send") == 0) {
            return ResponseUtils.get(20001, null, sign);
        }
        return ResponseUtils.get(20000, null, null);
    }

    @RequestMapping(value = "/phone/exist/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> existPhone(@PathVariable String phone) {
        boolean flag = userService.existPhone(phone);
        return ResponseUtils.get(20000, null, flag);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST )
    public ResponseEntity<Object> login(String fb_user_phone,
            String fb_user_password) {
        String token = null;

        try {
            token = userService.login(fb_user_phone, fb_user_password);
        } catch (UserNotFoundException e) {
            return ResponseUtils.get(20001, null, "用户不存在");
        } catch (InvalidPasswordException e) {
            return ResponseUtils.get(20002, null, "密码错误");
        }
        return ResponseUtils.get(20000, null, token);
    }
    
    @RequestMapping(value="/token",method=RequestMethod.POST)
    public ResponseEntity<Object> validToken(String fb_user_token){
        boolean validFlag = userService.validToken(fb_user_token);
        if(validFlag){
            return ResponseUtils.get(20000, null, null);
        }else{
            return ResponseUtils.get(20001, null, null);
        }
        
    }
}

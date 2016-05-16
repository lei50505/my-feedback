package cn.rest.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.User;
import cn.rest.service.UserService;
import cn.rest.util.ConfigUtils;
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
    public ResponseEntity<Object> add(User user, Integer fb_sign,
            HttpSession session) {
        int validSign=0;
        try {
            validSign = (int) session.getServletContext().getAttribute(
                    "fb_sign_" + user.getFb_user_phone());
        } catch (Exception e) {
            return ResponseUtils.get(40002, "没有发送验证码", null);
        }
        if (fb_sign != validSign) {
            return ResponseUtils.get(40001, "验证码不正确", null);
        }
        userService.add(user);
        User newUser = userService.getByPk(user.getFb_user_id());
        return ResponseUtils.get(20000, null, newUser);
    }

    @RequestMapping(value = "/sign/{phone}", method = RequestMethod.GET)
    public ResponseEntity<Object> getSign(@PathVariable String phone,
            HttpSession session) {
        int sign = userService.getSign(phone);
        session.getServletContext().setAttribute("fb_sign_" + phone, sign);
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            
            @Override
            public void run() {
                session.getServletContext().removeAttribute("fb_sign_" + phone);
                timer.cancel();
            }
        };
        timer.schedule(tt, 1000*10);
        if(ConfigUtils.getInt("conf.msg.send")==0){
            return ResponseUtils.get(20001, null, sign);
        }
        return ResponseUtils.get(20000, null, null);
    }
}

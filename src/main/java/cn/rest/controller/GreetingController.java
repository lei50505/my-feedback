package cn.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.User;
import cn.rest.service.UserService;

@RestController
public class GreetingController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello.do", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User(0, "dasdsa", "ew", "ewqeqw", "da", 1, "sa", null, null);
        userService.add(user);
        map.put("message",user);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @RequestMapping(value = "/isExistPhone.do", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> isExistPhone(String phone) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = userService.isExistPhone(phone);
        map.put("message",flag);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
}

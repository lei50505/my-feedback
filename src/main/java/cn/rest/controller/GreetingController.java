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
import cn.rest.util.ConfigUtils;

@RestController
public class GreetingController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello.do", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User("而且",1);
        userService.add(user);
        map.put("message", user.getId());
        System.out.println(ConfigUtils.getString("db.driver"));
        
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
}

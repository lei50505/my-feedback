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
import cn.rest.service.FeedbackService;
import cn.rest.service.ShopService;
import cn.rest.service.ShopTaskService;
import cn.rest.service.UserService;

@RestController
public class GreetingController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private ShopTaskService shopTaskService;
    
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> map = new HashMap<String, Object>();
//        User user = new User(null,null, "张三","15326761237","9876567",null,null, null, null, null);
//        userService.add(user);
//        Shop shop = new Shop(null, "a", "15326761239", "c", "23", "搜索`", null, null, null,null);
//        shopService.add(shop);
//        ShopTask st = new ShopTask(null, shop.getFb_shop_id(), "a", null, null, null);
//        shopTaskService.add(st);
//        Feedback feedback = new Feedback(null, user.getFb_user_id(), shop.getFb_shop_id(), "a", null, 0, null, null, null);
//        feedbackService.add(feedback);
//        shopTaskService.deleteByPk(2);
        User user = userService.getByPk(2);
        map.put("message",user);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @RequestMapping(value = "/isExistPhone.do", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> isExistPhone(String phone) {
        Map<String, Object> map = new HashMap<String, Object>();
//        boolean flag = userService.isExistedPhone(phone);
        map.put("message",1);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
}

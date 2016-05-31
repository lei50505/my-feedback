package cn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.rest.entity.ShopTask;
import cn.rest.service.ShopTaskService;

@RequestMapping("/shop-task")
@RestController
public class ShopTaskController {

    @Autowired
    private ShopTaskService shopTaskService;
    
    public ResponseEntity<Object> add(ShopTask shopTask){
        return null;
    }
    
}

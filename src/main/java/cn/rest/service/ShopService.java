package cn.rest.service;

import cn.rest.entity.Shop;

public interface ShopService {
    int sendEmailSign(String email) ;

    void addShop(Shop shop, Integer sign) ;
    
    String login(String email,String psw);
    
    Shop getShopById(int shopId);
}

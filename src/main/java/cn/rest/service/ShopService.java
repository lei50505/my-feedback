package cn.rest.service;

import cn.rest.entity.Shop;

public interface ShopService {
    void add(Shop shop);
    void updateByIdSelective(Shop shop);
    void updateById(Shop shop);
    Shop getById(Integer fb_shop_id);
    Shop getByEmail(String fb_shop_email);
    Shop getByToken(String fb_shop_token);
    
    void addShopWithSign(Shop shop, Integer sign) ;
    int sendEmailSign(String email) ;

    
    String login(String email,String psw);
    
}

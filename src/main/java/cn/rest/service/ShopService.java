package cn.rest.service;

import cn.rest.entity.Shop;
import cn.rest.exception.ServiceException;

public interface ShopService {
    int sendEmailSign(String email) throws ServiceException;

    void addShop(Shop shop, Integer sign) throws ServiceException;
    
    String login(String email,String psw)throws ServiceException;
    
    Shop getShopById(int shopId)throws ServiceException;
}

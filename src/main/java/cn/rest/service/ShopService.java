package cn.rest.service;

import cn.rest.entity.Shop;
import cn.rest.exception.EmailExistException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;

public interface ShopService {
    int sendEmailSign(String email) throws ParamFormatException,
            SystemException, EmailExistException;

    void addShop(Shop shop, Integer sign) throws ParamFormatException,
            SystemException, SignNotFoundException, InvalidSignException,
            EmailExistException;
}

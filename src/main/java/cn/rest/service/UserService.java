package cn.rest.service;

import cn.rest.entity.User;
import cn.rest.exception.ServiceException;

public interface UserService {

    String userLogin(String phone, String psw) throws ServiceException;

    void addUserBySign(User user, Integer sign) throws ServiceException;

    int sendPhoneMsgSign(String phone) throws ServiceException;

    boolean existPhone(String phone) throws ServiceException;
    
    User getUserByToken(String token) throws ServiceException;

}

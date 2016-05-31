package cn.rest.service;

import cn.rest.entity.User;

public interface UserService {

    String userLogin(String phone, String psw) ;

    void addUserBySign(User user, Integer sign) ;

    int sendPhoneMsgSign(String phone) ;

    boolean existPhone(String phone) ;
    
    User getUserByToken(String token) ;
    User getUserById(int fb_user_id) ;

}

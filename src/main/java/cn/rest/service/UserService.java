package cn.rest.service;

import java.util.List;

import cn.rest.entity.Feedback;
import cn.rest.entity.Shop;
import cn.rest.entity.User;

public interface UserService {

    void add(User user);

    void updateByIdSelective(User user);

    void updateById(User user);

    User getById(Integer fb_user_id);

    User getByPhone(String fb_user_phone);

    User getByToken(String fb_user_token);

    String userLogin(String fb_user_phone, String inputPsw);

    void addUserBySign(User user, Integer sign);

    int sendPhoneMsgSign(String fb_user_phone);

    boolean existPhone(String fb_user_phone);
    
    User getValidUserByToken(String token);
    
    Shop getShopByUserToken(String fb_user_token);
    
    List<Feedback> getPageByUserToken(String fb_user_token,int page);

}

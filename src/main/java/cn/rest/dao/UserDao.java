package cn.rest.dao;

import cn.rest.entity.User;

public interface UserDao {
    void insert(User user);
    void updateByPkSelective(User user);
    void updateByPk(User user);
    User selectByPk(Integer fb_user_id);
    User getByPhone(String fb_user_phone);
    User getByToken(String fb_user_token);
}

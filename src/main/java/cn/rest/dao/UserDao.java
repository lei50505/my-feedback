package cn.rest.dao;

import cn.rest.entity.User;

public interface UserDao {
    void add(User user);
    void updateByIdSelective(User user);
    void updateById(User user);
    User getById(Integer fb_user_id);
    User getByPhone(String fb_user_phone);
    User getByToken(String fb_user_token);
}

package cn.rest.dao;

import cn.rest.entity.User;

public interface UserDao {
    void insert(User user);
    void updateByPkSelective(User user);
    User selectByPk(Integer fb_user_id);
}

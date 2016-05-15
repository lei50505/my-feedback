package cn.rest.service;

import cn.rest.entity.User;


public interface UserService {
    void add(User user);
    User getByPk(int userId);
}

package cn.rest.service;

import cn.rest.entity.User;


public interface UserService {
    int add(User user);
    User getByPk(int userId);
    int getSign(String phone);
}

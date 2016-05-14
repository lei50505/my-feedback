package cn.rest.service;

import cn.rest.entity.User;


public interface UserService {
    public void add(User user);
    public boolean isExistedPhone(String phone);
}

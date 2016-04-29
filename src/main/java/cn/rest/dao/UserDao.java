package cn.rest.dao;

import java.util.List;

import cn.rest.entity.User;

public interface UserDao {
    public void insert(User user);
    public List<User> getByPhone(String phone);
}

package cn.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rest.dao.UserDao;
import cn.rest.entity.User;
import cn.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public void add(User user) {
        userDao.insert(user);
    }
    
}
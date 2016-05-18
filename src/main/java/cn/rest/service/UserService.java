package cn.rest.service;

import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.UserNotFoundException;

public interface UserService {
    int add(User user);

    User getByPk(int userId);

    int getSign(String phone);

    boolean existPhone(String phone);

    String login(String phone,String psw) throws UserNotFoundException,
            InvalidPasswordException;
    
    boolean validToken(String token);
    
    void update(User user);
}

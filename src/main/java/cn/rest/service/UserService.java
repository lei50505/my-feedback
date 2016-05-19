package cn.rest.service;

import cn.rest.entity.User;
import cn.rest.exception.InvalidPasswordException;
import cn.rest.exception.InvalidSignException;
import cn.rest.exception.ParamFormatException;
import cn.rest.exception.PhoneExistException;
import cn.rest.exception.SignNotFoundException;
import cn.rest.exception.SystemException;
import cn.rest.exception.UserExistException;
import cn.rest.exception.UserNotFoundException;

public interface UserService {

    String userLogin(String phone, String psw) throws UserNotFoundException,
            InvalidPasswordException, ParamFormatException, SystemException;

    void addUserBySign(User user, Integer sign) throws ParamFormatException,
            UserExistException, SystemException, SignNotFoundException,
            InvalidSignException;

    int getMsgSign(String phone) throws SystemException, ParamFormatException,
            PhoneExistException;

    boolean existPhone(String phone) throws ParamFormatException,
            SystemException;

}

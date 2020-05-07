package com.myshop.prototype.service;

import com.myshop.prototype.bean.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User insertUser(User user);
    void deleteUser(Long uid);
    User updateUser(User user);
    List<User> selectAll();
    Optional<User> selectUser(Long uid);

    List<User> loginSelect(String accid, String pwd);
    List<User> multiCheckByAccid(String accid);

}

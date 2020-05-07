package com.myshop.prototype.impl;

import com.myshop.prototype.bean.User;
import com.myshop.prototype.repo.UserRepo;
import com.myshop.prototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User insertUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long uid) {
        userRepo.deleteById(uid);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> selectAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> selectUser(Long uid) {
        return userRepo.findById(uid);
    }

    @Override
    public List<User> loginSelect(String accid, String pwd) {
        return userRepo.findByAccidAndPwd(accid, pwd);
    }

    @Override
    public List<User> multiCheckByAccid(String accid) {
        return userRepo.findByAccid(accid);
    }
}

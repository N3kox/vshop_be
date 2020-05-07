package com.myshop.prototype.repo;

import com.myshop.prototype.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public List<User> findByAccidAndPwd(String accid, String pwd);
    public List<User> findByAccid(String accid);
}

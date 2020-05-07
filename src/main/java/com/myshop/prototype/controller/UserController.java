package com.myshop.prototype.controller;

import com.myshop.prototype.bean.User;
import com.myshop.prototype.impl.UserImpl;
import com.myshop.prototype.utils.md5;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController extends UserImpl {
    private Integer ULEVEL_BASIC = Integer.valueOf(1);
    private Integer ULEVEL_ADMIN = Integer.valueOf(2);
    private Integer ULEVEL_SUDO = Integer.valueOf(5);

    //登录用（原密码）
    @GetMapping("/login")
    @ResponseBody
    public User login(@Param("accid") String accid, @Param("pwd") String pwd){
        if(accid == null || pwd == null)
            return null;    //accid pwd default
       List<User> ulist = loginSelect(accid, md5.originMD5(pwd));
       if(ulist.size() == 0)
           return null;
       return ulist.get(0);
    }

    //uid获取用户信息
    @GetMapping("/get")
    @ResponseBody
    public User getUserInfo(@Param("uid") Long uid){
        if(uid == null) return null;
        Optional<User> user = selectUser(uid);
        return user.orElse(null);
    }

    //新增用户信息(register)
    @PostMapping("/insert")
    @ResponseBody
    public User addUserInfo(@Param("accid") String accid, @Param("pwd") String pwd, @Param("name") String name){
        //保证注册完整性
        if(accid == null || pwd == null || name == null)
            return null;
        User nu = new User();
        List<User> checkList = multiCheckByAccid(accid);
        if(checkList.size() > 0)
            return null;    //重复
        nu.setAccid(accid);
        nu.setPwd(md5.originMD5(pwd));
        nu.setName(name);
        nu.setLevel(ULEVEL_BASIC);
        insertUser(nu);
        return nu;
    }

    //修改用户信息(fe)
    @PutMapping("/update")
    @ResponseBody
    public User updateUserInfo(@Param("uid") Long uid, @Param("name")String name, @Param("pwd") String pwd){
        User cur = getUserInfo(uid);
        if(name != null) cur.setName(name);
        if(pwd != null) cur.setPwd(md5.originMD5(pwd));
        return updateUser(cur);
    }


    //删除用户信息
    @DeleteMapping("/delete")
    @ResponseBody
    public String deleteUserInfo(@Param("uid")Long uid){
        deleteUser(uid);
        return "ok";
    }

    //获取全部用户信息
    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUserInfo(){
        return selectAll();
    }


}

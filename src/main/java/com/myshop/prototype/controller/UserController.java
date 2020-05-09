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

    //更新名称
    @PutMapping("/updateName")
    @ResponseBody
    public User updateUserInfo(@Param("uid") Long uid, @Param("name")String name){
        if(uid == null)
            return null;
        User cur = getUserInfo(uid);
        if(name != null) cur.setName(name);
        return updateUser(cur);
    }

    //更新密码
    @PutMapping("/updatePwd")
    @ResponseBody
    public User updateUserInfo(@Param("uid") Long uid, @Param("pwd") String pwd, @Param("newpwd") String newpwd) {
//        System.out.println("uid : "+ uid);
//        System.out.println("pwd : " + pwd);
//        System.out.println("curpwd : " + newpwd);
        if (uid == null || pwd == null || newpwd == null)
            return null;
        User cur = getUserInfo(uid);
//        System.out.println("curPwd : "+md5.originMD5(pwd));
//        System.out.println("newPwd : "+cur.getPwd());
        if (md5.originMD5(pwd).equals(cur.getPwd())) {
            cur.setPwd(md5.originMD5(newpwd));
        }
        return updateUser(cur);
    }

    //更新level
    @PutMapping("/updateLevel")
    @ResponseBody
    public User updateUserLevel(@Param("uid") Long uid, @Param("level") Integer level){
        if(uid == null || level == null)
            return null;
        User nu = getUserInfo(uid);
        nu.setLevel(level);
        return updateUser(nu);
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


    @PutMapping("/pwdforce")
    @ResponseBody
    public User pwdForceUpdate(@Param("uid") Long uid, @Param("newpwd") String newpwd){
        if(uid == null || newpwd == null) return null;
        User cur = getUserInfo(uid);
        if(cur == null) return null;
        cur.setPwd(md5.originMD5(newpwd));
        return updateUser(cur);
    }

}

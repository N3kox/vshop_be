package com.myshop.prototype.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestRepo testRepository;

    @RequestMapping("/toHello")
    public String toHello(ModelMap modelMap){
        Test user = new Test();
        user.setName("newUser");
        user.setOpenid("123456");
        user.setPwd("eatshit");
        testRepository.save(user);
        List<Test> users = testRepository.findAll();
        modelMap.put("users",users);
        return "helloBoot";   //页面地址
    }

    @GetMapping("/user")
    @ResponseBody
    public List<Test> getUserInfo(){
        List<Test> all = testRepository.findAll();
        System.out.println(all.size());
        for(Test u : all){
            System.out.println("name:"+u.getName());
        }
        return all;
    }

}

package com.myshop.prototype.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBoot {
    @RequestMapping("helloBoot")
    public String helloBoot(){
        return "Hello Boot-JPA";
    }
}

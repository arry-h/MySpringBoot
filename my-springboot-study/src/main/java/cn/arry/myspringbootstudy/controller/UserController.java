package cn.arry.myspringbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arry
 * @description
 * @since 1.0
 */
@RestController
public class UserController {

    @RequestMapping("test")
    public String test(){
        System.out.println("testxxxxxxxxxxxxxxxxxxxxxx");
        return "TEST";
    }
}

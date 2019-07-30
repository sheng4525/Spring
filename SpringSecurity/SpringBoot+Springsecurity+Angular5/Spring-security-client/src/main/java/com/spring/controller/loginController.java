package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.vo.JsonResult;

@RestController
public class loginController {
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/test")
    public JsonResult user() {
        return JsonResult.ok("/user,具有ROLE_USER可以访问");
    }

    @RequestMapping("/admin")
    public JsonResult admin() {
        return JsonResult.ok("/admin,具有ROLE_ADMIN可以访问");
    }

}

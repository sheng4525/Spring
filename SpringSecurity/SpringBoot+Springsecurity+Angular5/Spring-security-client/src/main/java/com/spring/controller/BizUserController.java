package com.spring.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.BizUser;
import com.spring.service.BizUserService;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class BizUserController {
	
	@Autowired
	private BizUserService bizUserService;
	
	 /**
     * 获取登录用户信息
     *
     * @param authentication
     * @return
     */
    @PostMapping("/finduser")
    public List<BizUser> findUserByName(Authentication authentication){
        return bizUserService.findBizUser();
    }
    
    @PostMapping("/createuser")
    public void createBizUser(@RequestBody BizUser bizUser) throws NoSuchAlgorithmException {
    	bizUserService.createBizUser(bizUser);
    }

    @PostMapping("/updateuser")
    public void updateBizUser(@RequestBody BizUser bizUser) throws NoSuchAlgorithmException {
    	bizUserService.updateBizUser(bizUser);
    }
    
    @PostMapping("/deleteuser")
    public void deleteBizUser(@RequestBody Long userId){
    	bizUserService.deleteBizUser(userId);
    }

    @PostMapping("/changepassword")
    public void changePassword(String username,String newpassword,String oldpassword) throws NoSuchAlgorithmException {
    	bizUserService.changePassword(username, newpassword, oldpassword);
    }
}

package com.spring.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.model.BizUser;


public interface BizUserService extends UserDetailsService {

	
	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	public BizUser createBizUser(BizUser user);
	
	/**
	 * 用户查询列表
	 * @return
	 */
	public List<BizUser> findBizUser();
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteBizUser(Long id);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Boolean updateBizUser(BizUser user);
	
	/**
	 * 修改密码
	 * @param username
	 * @param newpassword
	 * @param oldpassword
	 * @return
	 */
	public Boolean changePassword(String username,String newpassword,String oldpassword);  
	
}

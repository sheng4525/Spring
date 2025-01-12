package com.spring.service.impl;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.model.BizUser;
import com.spring.repository.BizUserRepository;
import com.spring.service.BizUserService;
import com.spring.util.AuthorityUtil;

/**
 * spring-security 自带接口实现
 * @author pengsheng
 *
 */
@CacheConfig(cacheNames = "user") // 类级别缓存，设置缓存 key 前缀之类的
@Service
public class UserDetailServiceImpl implements BizUserService {

	@Resource
	BizUserRepository bizUserRepository;
	
	//value属性为key指定前缀
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BizUser bizUser = bizUserRepository.findByUsername(username);
		return create(bizUser); 
	}

	//改变对象属性如：loginId改成username
	private static org.springframework.security.core.userdetails.User create(BizUser user) {
		//方案1
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
        //方案2
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtil.createGrantedAuthorities(user.getRoleList()));
		//方案3
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
				user.getAccountNonExpired(), user.getCredentialsNonExpired(),
				user.getAccountNonLocked(), AuthorityUtil.createGrantedAuthorities(user.getRoleList()));
    }

	/**
	 * 触发缓存入口
	 * 方法级别注解，根据 key 查询缓存
	 */
	@Cacheable(value = "user",key = "'createbizuser'+#user")
	@Override
	public BizUser createBizUser(BizUser user) {
		try {
			Date date = new Date();
			Boolean bool = Boolean.TRUE;
			user.setCreated(date);
			user.setUpdated(date);
			user.setEnabled(bool);
			user.setAccountNonExpired(bool);
			user.setAccountNonLocked(bool);
			user.setCredentialsNonExpired(bool);
			int strength = 8;
	        SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
	        BCryptPasswordEncoder e = new BCryptPasswordEncoder(strength,random);//向spring注册一个密码加密器
	        user.setPassword(e.encode(user.getPassword()));
			return bizUserRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新缓存
	 * 用于更新缓存，每次调用都会想 db 请求，缓存数据
	 * value 表示类级别缓存
	 */
	@CachePut(value = "user", key = "'findBizUser'")
	@Override
	public List<BizUser> findBizUser() {
		return bizUserRepository.findAll();
	}
	
	/**
	 * 根据 key 删除缓存中的数据。allEntries=true 表示删除缓存中所有数据
	 */
	@CacheEvict(value = "user", key = "'deleteBizUser'+#id",allEntries=false)
	@Override
	public void deleteBizUser(Long id) {
		bizUserRepository.deleteById(id);
	}

	/**
	 * 更新缓存
	 * 用于更新缓存，每次调用都会想 db 请求，缓存数据
	 * value 表示类级别缓存
	 */
	@CachePut(value = "user", key = "'bizuser'+#user.getId()")
	@Override
	public Boolean updateBizUser(BizUser user) {
		try {
			bizUserRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 更新缓存
	 * 用于更新缓存，每次调用都会想 db 请求，缓存数据
	 * value 表示类级别缓存
	 */
	@CachePut(value = "user", key = "'changePassword'+#username")
	@Override
	public Boolean changePassword(String username, String newpassword, String oldpassword) {
		try {
			// 获取当前登录对象
			//org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = AuthorityUtil.getUser(); //进行封装的
			// 获取用户加密后密码
			String pass = user.getPassword();
			// 比对原始密码
			int strength = 8;
	        SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
	        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(strength,random);//向spring注册一个密码加密器
	        
			// 判断加密后密码一致性
			boolean f = bcryptPasswordEncoder.matches(oldpassword, pass);
			if (f) {
				// 如果一致，获取对象后修改密码
				// 根据用户查找对象
				BizUser bizUser2 = bizUserRepository.findByUsername(user.getUsername());
				
				// 密码加密后修改
				String hashPass = bcryptPasswordEncoder.encode(newpassword);
				bizUser2.setPassword(hashPass);
				
				// 修改密码
				BizUser save = bizUserRepository.save(bizUser2);
				if (save != null) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}

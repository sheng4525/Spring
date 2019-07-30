package com.spring.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.model.BizRole;

/**
 * UserDetails Authority转换工具类
 * @author pengsheng
 *
 */
public class AuthorityUtil {
	
	private AuthorityUtil() {
    }

    public static List<GrantedAuthority> createGrantedAuthorities(List<BizRole> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }

    public static List<GrantedAuthority> createGrantedAuthorities(String... authorities) {
        return Stream.of(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static String[] getAuthorities(UserDetails user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }
    
    public static org.springframework.security.core.userdetails.User getUser() {
		return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}

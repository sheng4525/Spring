package com.spring.config;

import static org.springframework.http.HttpMethod.POST;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.service.BizUserService;
import com.spring.util.AuthorityName;
import com.spring.vo.JsonResult;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Resource
    //UserDetailsService userDetailsService;//上一节中的实现类,这个是security自带接口
	
	@Resource
	BizUserService bizUserService;
	
    @Resource
    ObjectMapper objectMapper;
    
    /**
     *  permitAll 允许所有人访问文件或目录
		hasRoles 传递多个角色
        access 用于复杂的访问限制
       .antMatchers("/static","/register").permitAll()
       .antMatchers("/user/**").hasRoles("USER", "ADMIN") // can pass multiple roles
       .antMatchers("/admin/**").access("hasRole('ADMIN') and hasIpAddress('123.123.123.123')") // pass SPEL using access method
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()//表单验证，为了简单就不搞token验证了
                .loginProcessingUrl("/login")//处理登录请求的api
                //因为表单验证方式默认是跳转页面，而我们前后分离不需要后端处理跳转
                //所以自定义一个登录成功处理器，它只需要告诉我们登录结果就可以了
                .successHandler(successHandler())//登录成功处理器
                .failureHandler(failureHandler())//登录失败处理器
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())//登出处理器
                .logoutUrl("/logout")//登出api
                .permitAll()
                .and().authorizeRequests()//接下来进行鉴权拦截，有相应权限的才可以从接口中得到数据
                .antMatchers("/" , "/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
             // .anyRequest().authenticated() //任何请求都需要验证
                .and().cors()//跨域设置
                .and().csrf().disable() //关掉csrf防御，将防护跨站请求伪造的功能置为不可用
        		.headers().cacheControl(); // 禁用页面缓存 
        
        
    }

    /**
     * 自定义登录成功处理器，成功返回一个带有成功信息的Json数据包装类
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("登录成功");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }

    /**
     * 自定义登录失败处理器，成功返回一个带有失败信息的Json数据包装类
     */
    private AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult error = JsonResult.error("账号或密码错误");
            out.write(objectMapper.writeValueAsString(error));
            out.flush();
            out.close();
        };
    }

    /**
     * 自定义登出成功处理器，清除登录信息且成功返回一个带有登出信息的Json数据包装类
     */
    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);//清除登录认证信息
            }
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("注销成功");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }
    
    /**
     * 加密技术
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException, NoSuchProviderException {
    	//方案1
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();//向spring注册一个密码加密器
       
       /** 参数说明
        * @param strength the log rounds to use, between 4 and 31
        * @param random the secure random instance to use
        */
       int strength = 8;
       SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
       //random = SecureRandom.getInstance("SHA1PRNG","RUN"); //指定了算法名称又指定了包提供程序
       
       //方案2
       bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);//向spring注册一个密码加密器
       //方案3
       bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, random);//向spring注册一个密码加密器
       
       return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(bizUserService).passwordEncoder(passwordEncoder());//配置密码加密器
    }

    //加密密码生成方法,生成自己想要的密码
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int strength = 8;
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
        BCryptPasswordEncoder e = new BCryptPasswordEncoder(strength,random);//向spring注册一个密码加密器
        String encode = e.encode("123456");
        
        System.out.println(encode);
    }

}

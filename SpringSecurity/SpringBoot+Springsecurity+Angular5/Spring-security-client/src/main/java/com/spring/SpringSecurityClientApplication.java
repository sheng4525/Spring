package com.spring;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.model.BizRole;
import com.spring.model.BizUser;
import com.spring.repository.BizRoleRepository;
import com.spring.repository.BizUserRepository;
import com.spring.util.AuthorityName;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.spring.repository"})
@EntityScan(basePackages = {"com.spring.model"})
@EnableJpaAuditing
public class SpringSecurityClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityClientApplication.class, args);
	}
	
	@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
	
	/**
     * 初始化测试数据
     * @param userRepository
     * @param roleRepository
     * @return
     */
//    @Bean
//    CommandLineRunner initData(BizUserRepository userRepository,BizRoleRepository roleRepository) {
//        return args -> {
//            BizRole admin = roleRepository.findByName(AuthorityName.ROLE_ADMIN.name());
//            if (ObjectUtils.isEmpty(admin)) {
//                admin=new BizRole();
//                admin.setName(AuthorityName.ROLE_ADMIN.name());
//                admin.setNote("管理员");
//                roleRepository.saveAndFlush(admin);
//            }
//            
//            BizRole user = roleRepository.findByName(AuthorityName.ROLE_USER.name());
//            if (ObjectUtils.isEmpty(user)) {
//                user=new BizRole();
//                user.setName(AuthorityName.ROLE_USER.name());
//                user.setNote("用户");
//                roleRepository.saveAndFlush(user);
//            }
//            
//            BizUser zz = userRepository.findByUsername("admin");
//            if (ObjectUtils.isEmpty(zz)) {
//            	int strength = 8;
//                SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
//                BCryptPasswordEncoder e = new BCryptPasswordEncoder(strength,random);//向spring注册一个密码加密器
//                String encode = e.encode("123456");
//                
//                zz=new BizUser();
//                zz.setLoginId("admin");
//                zz.setUsername("admin");
//                //zz.setPassword("$2a$08$Qe5.pihtpQyziOSNbjfoLOBayZ5T0GFjVEi1RY.DtbFifI4cDXP9i");//123456
//                zz.setPassword(encode);
//                zz.setLoginId("admin");
//                zz.setCreated(new Date());
//                zz.setUpdated(new Date());
//                zz.setEnabled(Boolean.TRUE);
//                zz.setAccountNonExpired(Boolean.TRUE);
//                zz.setCredentialsNonExpired(Boolean.TRUE);
//                zz.setAccountNonLocked(Boolean.TRUE);
//                userRepository.save(zz);
//                List<BizRole> zzRoles=new ArrayList<>();
//                zzRoles.add(admin);
//                zzRoles.add(user);
//                zz.setRoleList(zzRoles);
//                userRepository.save(zz);
//            }
//            
//            BizUser zls = userRepository.findByUsername("user");
//            if (ObjectUtils.isEmpty(zls)) {
//            	int strength = 8;
//                SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); //仅指定算法名称
//                BCryptPasswordEncoder e = new BCryptPasswordEncoder(strength,random);//向spring注册一个密码加密器
//                String encode = e.encode("123456");
//                
//                zls=new BizUser();
//                zls.setUsername("user");
//                //zls.setPassword("$2a$10$99UTVBaagS09sKyzYKoqiuW6wSWzoAWu5.PegN6tSY0HxllXt62S.");//123456
//                zz.setPassword(encode);
//                zls.setLoginId("user");
//                zls.setCreated(new Date());
//                zls.setUpdated(new Date());
//                zls.setEnabled(Boolean.TRUE);
//                zls.setAccountNonExpired(Boolean.TRUE);
//                zls.setCredentialsNonExpired(Boolean.TRUE);
//                zls.setAccountNonLocked(Boolean.TRUE);
//                userRepository.save(zls);
//                List<BizRole> zlsRoles=new ArrayList<>();
//                zlsRoles.add(user);
//                zls.setRoleList(zlsRoles);
//                userRepository.save(zls);
//            }
//            
//        };
//    }


}

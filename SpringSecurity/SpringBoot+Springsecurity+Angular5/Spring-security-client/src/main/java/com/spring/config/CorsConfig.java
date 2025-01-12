package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 跨域处理
 * 前后分离的项目肯定会涉及到跨域问题。为什么跨域，跨域的概念这里就不赘述了，不了解的同学可以自己查看。我知道的跨域方式有两种，
 * 其中一种是jsonp（想了解自己查一下），另一种就是设置http跨域头来实现跨域
 * @author pengsheng
 *
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
	private CorsConfiguration buildConfig() {
	       CorsConfiguration corsConfiguration = new CorsConfiguration();
	       corsConfiguration.addAllowedOrigin("*");
	       corsConfiguration.addAllowedHeader("*");
	       corsConfiguration.addAllowedMethod("*");
	       //默认的跨域请求不会发送cookie等用户认证凭据
	       //必须前端设置withCredentials=true且后端设置AllowCredentials(true)
	       //才能使cookie+session认证生效;
	       corsConfiguration.setAllowCredentials(true);
	       return corsConfiguration;
	   }

	   @Bean
	   public CorsFilter corsFilter() {
	       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	       source.registerCorsConfiguration("/**", buildConfig());
	       return new CorsFilter(source);
	   }

	   @Override
	   public void addCorsMappings(CorsRegistry registry) {
	       registry.addMapping("/**")
	               .allowedOrigins("*")
	               .allowCredentials(true)
	               .allowedMethods("GET", "POST", "DELETE", "PUT")
	               .maxAge(3600);
	   }

}

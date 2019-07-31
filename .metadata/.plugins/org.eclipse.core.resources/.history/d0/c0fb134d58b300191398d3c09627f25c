package com.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
public class Config {
	
	private @Getter @Setter Cors cors = new Cors();

    private @Getter @Setter Jwt jwt = new Jwt();
	
	public static class Cors {
        private @Getter @Setter List<String> allowedOrigins = new ArrayList<>();

        private @Getter @Setter List<String> allowedMethods = new ArrayList<>();

        private @Getter @Setter List<String> allowedHeaders = new ArrayList<>();

    }

    public static class Jwt {
        private @Getter @Setter String header;

        private @Getter @Setter String secret;

        private @Getter @Setter Long expiration;

        private @Getter @Setter String issuer;

        private @Getter @Setter String authenticationPath;
    }
}

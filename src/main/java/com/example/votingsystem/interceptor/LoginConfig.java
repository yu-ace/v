package com.example.votingsystem.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns(
//                "/login",
//                "/",
//                "/send",
//                "/checkVerificationCode",
//                "/**/*.html",
//                "/**/*.js",
//                "/**/*.css"
//        );
//    }
}

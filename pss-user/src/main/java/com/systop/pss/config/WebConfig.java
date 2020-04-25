package com.systop.pss.config;

import com.systop.pss.interceptor.LoginInterceptor;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName WebConfig
 * PackageName com.systop.pss.config
 *
 * @Description
 * @auther wang
 * @create 2019-12-11
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器拦截路径
//        String[] patterns = {"/**"};
//        String[] excludePath = {"/login/*","/user/login"};
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(patterns).excludePathPatterns(excludePath);
    }
}

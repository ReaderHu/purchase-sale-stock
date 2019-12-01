package com.systop.pss.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wang
 * @since 2019/11/30
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket ApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("userApi")
				.apiInfo(adminApiInfo())
				.select()
				.paths(Predicates.and(PathSelectors.regex("/user/.*")))
				.build();
	}

	private ApiInfo adminApiInfo(){

		return new ApiInfoBuilder()
				.title("工厂工人管理")
				.description("本文档描述了后台管理系统课程中心微服务接口定义")
				.version("1.0")
				.contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
				.build();
	}

}

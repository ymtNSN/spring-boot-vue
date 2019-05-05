package com.ymt.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/4/30
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket docketApp() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("app")
                .apiInfo(apiInfo())
                .select()
                // 当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.ymt.manage.app"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("web")
                .apiInfo(apiInfo())
                .select()
                // 当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.ymt.manage.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springBoot 使用swagger2构建RESTful API")
                .version("1.0")
                .description("API 描述")
                .build();
    }
}

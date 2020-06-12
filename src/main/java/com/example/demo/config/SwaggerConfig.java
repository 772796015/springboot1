package com.example.demo.config;

/**
 * Created by DJ010199 on 2020/4/14.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

    @Configuration
    @EnableSwagger2
    public class SwaggerConfig extends WebMvcConfigurationSupport {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.demo.example"))// 指定扫描包下面的注解
                    .paths(PathSelectors.any())
                    .build();
        }
        // 创建api的基本信息
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("集成Swagger2构建RESTful APIs")
                    .description("集成Swagger2构建RESTful APIs")
                    .termsOfServiceUrl("https://www.baidu.com")
                    .contact("zhangsan")
                    .version("1.0.0")
                    .build();
        }

        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            // 解决静态资源无法访问
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            // 解决swagger无法访问
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            // 解决swagger的js文件无法访问
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        }

    }


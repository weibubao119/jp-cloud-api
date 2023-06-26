package com.jp.common.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;


@EnableOpenApi
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
//    private final SwaggerProperties swaggerProperties;
//
//
//    public SwaggerConfig(SwaggerProperties swaggerProperties) {
//        this.swaggerProperties = swaggerProperties;
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")

                // 定义是否开启swagger，false为关闭，可以通过变量控制
//                .enable(swaggerProperties.getEnable())
                //.protocols(Sets.newHashSet("application/octet-stream"))

                // 将api的元信息设置为包含在json ResourceListing响应中。
//                .apiInfo(apiInfo())

                // 接口调试地址
                //.host(swaggerProperties.getTryHost())

                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/.*/error").negate())
                .build();

//                // 支持的通讯协议集合
//                .protocols(newHashSet("https", "http"))
//
//                // 授权信息设置，必要的header token等认证信息
//                .securitySchemes(securitySchemes())
//
//                // 授权信息全局应用
//                .securityContexts(securityContexts());

    }

//    /**
//     * API 页面上半部分展示信息
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title(swaggerProperties.getApplicationName())
//                .description(swaggerProperties.getApplicationDescription())
//                .contact(new Contact("sie", null, "xh@gmail.com"))
//                .version("Application Version: " + swaggerProperties.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())
//                .build();
//    }

//    /**
//     * 认证的安全上下文
//     */
//    private List<SecurityScheme> securitySchemes() {
//        List<SecurityScheme> securitySchemes = new ArrayList<>();
//        securitySchemes.add(new ApiKey("jpauth", "jpauth", "header"));
//        securitySchemes.add(new ApiKey("user-agent", "user-agent", "header"));
//        return securitySchemes;
//    }
//
//    /**
//     * 授权信息全局应用
//     */
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.any()).build());
//        return securityContexts;
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences = new ArrayList<>();
//        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
//        return securityReferences;
//    }
//
//
//    @SafeVarargs
//    private final <T> Set<T> newHashSet(T... ts) {
//        if (ts.length > 0) {
//            return new LinkedHashSet<>(Arrays.asList(ts));
//        }
//        return Collections.emptySet();
//    }
}
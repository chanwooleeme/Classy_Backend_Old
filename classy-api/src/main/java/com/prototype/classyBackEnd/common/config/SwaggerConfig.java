package com.prototype.classyBackEnd.common.config;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* 사용해보려고 했으나 FE와 협업하기에 불편하고 같은 에러라도 각기 다른 메시지를
* 전달할 수 없음.
* */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(AlternateTypeRules.newRule(Pageable.class, MyPage.class))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.prototype.classyBackEnd"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Classy Api Document")
                .description("Classy Api Document Config")
                .version("1.0")
                .build();
    }

    @Getter @Setter
    static class MyPage {
        @ApiParam(value = "페이지 번호 [0..N]", example = "0")
        private Integer page;

        @ApiParam(value = "페이지 크기 [0..N]", example = "5")
        private Integer size;

        @ApiParam(value = "정렬 [Column Name,ASC|DESC] (ex: videoId,ASC)")
        private String sort;
    }
}

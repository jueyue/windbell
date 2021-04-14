package com.wupaas.boot.admin.config;

import com.wupaas.boot.admin.common.exception.BizExceptionEnum;
import com.wupaas.boot.core.exception.BootExceptionEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 *
 * @author JueYue
 * @date 2017年6月1日19:42:59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createTradeRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("交易管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wupaas.boot.trade"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());
    }

    @Bean
    public Docket createPaasRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("三方服务管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wupaas.boot.paas"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());
    }

    @Bean
    public Docket createSystemRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("基础管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wupaas.boot.admin"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("风铃开发平台")
                .description("风铃 Api文档")
                .termsOfServiceUrl("http://www.wupaas.com/")
                .contact(new Contact("JueYue", "http://www.wupaas.com/", "qrb.jueyue@foxmail.com"))
                .version("1.0.1")
                .build();
    }


    public List<ResponseMessage> getResponseMessages() {
        List<ResponseMessage> rmList = new ArrayList<>();
        rmList.add(new ResponseMessageBuilder().code(200).message("成功").build());
        BootExceptionEnum[] boot = BootExceptionEnum.values();
        for (int i = 0; i < boot.length; i++) {
            rmList.add(
                    new ResponseMessageBuilder().code(boot[i].getCode()).message(boot[i].getMessage()).responseModel(new ModelRef("Error")).build());
        }
        BizExceptionEnum[] arr = BizExceptionEnum.values();
        for (int i = 0; i < arr.length; i++) {
            rmList.add(
                    new ResponseMessageBuilder().code(arr[i].getCode()).message(arr[i].getMessage()).responseModel(new ModelRef("Error")).build());
        }
        return rmList;
    }
}

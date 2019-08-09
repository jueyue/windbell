package cn.afterturn.boot.wupass.config;

import cn.afterturn.boot.bussiness.exception.BootExceptionEnum;
import cn.afterturn.boot.wupass.common.exception.BizExceptionEnum;
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
    public Docket createSystemRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("基础管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.afterturn.boot.paas"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lemur Boot Admin")
                .description("Lemur Boot Admin Api文档")
                .termsOfServiceUrl("http://www.afterturn.cn")
                .contact(new Contact("JueYue", "http://www.afterturn.cn", "qrb.jueyue@foxmail.com"))
                .version("1.0")
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

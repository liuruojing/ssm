package cn.jarvan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b><code>swaggerUI config</code></b>
 * <p>
 * \
 *
 * <b>Creation Time:</b> 2018年7月20日 下午15:47:10
 *
 * @author liuruojing
 * @version 0.1.0
 * @since ssm-rs 0.1.0
 */
@WebAppConfiguration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .groupName("ssm-rs")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.basePackage("cn.jarvan.controller"))
                .paths(PathSelectors.any())
                .build();
        watch.stop();
        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {
        String title = "ssm-rs 项目RESTFUL API文档";
        String description = " RESTFUL API";
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("0.1.0")
                .build();
    }
}
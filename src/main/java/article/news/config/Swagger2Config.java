package article.news.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Swagger Configuration
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        Set<String> strings = new HashSet<>();
        strings.add("application/json");

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("article.news.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                        .name("Authorization")
                        .description("JWT Authorization token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()))
                .apiInfo(apiEndPointsInfo()).produces(strings)
                .tags(
                        new Tag("Users", "User Management"),
                        new Tag("Roles", "Role Management"),
                        new Tag("Authentication", "User Authentication"),
                        new Tag("Authors", "Author Management"),
                        new Tag("News", "Published Articles")
                );
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("News REST API")
                .description("News REST API with multiple writers.")
                .contact(new Contact("Nero Okiewhru", "https://www.linkedin.com/in/oghenero-okiewhru/", "oghenero.okiewhru@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
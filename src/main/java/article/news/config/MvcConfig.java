package article.news.config;

import article.news.interceptor.AuthInterceptor;
import article.news.interceptor.AuthorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AuthorInterceptor authorInterceptor;

    @Autowired
    public MvcConfig(AuthInterceptor authInterceptor, AuthorInterceptor authorInterceptor) {
        this.authInterceptor = authInterceptor;
        this.authorInterceptor = authorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(
                "/authors/**",
                "/roles/**",
                "/users/**",
                "/session/logout"
        ).excludePathPatterns(
                "/authors/register"
        );

        registry.addInterceptor(authorInterceptor).addPathPatterns(
                "/authors/articles/**"
        );
    }
}


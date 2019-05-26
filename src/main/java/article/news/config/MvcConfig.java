package article.news.config;

import article.news.interceptor.AuthInterceptor;
import article.news.interceptor.AdministratorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Interceptors configuration
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AdministratorInterceptor administratorInterceptor;

    @Autowired
    public MvcConfig(AuthInterceptor authInterceptor, AdministratorInterceptor administratorInterceptor) {
        this.authInterceptor = authInterceptor;
        this.administratorInterceptor = administratorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(
                "/authors/**",
                "/news/**",
                "/roles/**",
                "/users/**",
                "/session/logout"
        ).excludePathPatterns(
                "/authors/register"
        );

        registry.addInterceptor(administratorInterceptor).addPathPatterns(
                "/roles/**",
                "/users/**"
        );
    }
}


package gamebook.gamebook;

import gamebook.gamebook.interceptor.LoginCheckInterceptor;
import gamebook.gamebook.interceptor.UnLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/new", "/login", "/logout", "/css/**", "/*.ico", "/error");

        registry.addInterceptor(new UnLoginInterceptor())
                .order(2)
                .addPathPatterns("/login", "/members/new");
    }
}

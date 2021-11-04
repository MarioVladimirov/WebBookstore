package bg.softuni.webbookstore.config;

import bg.softuni.webbookstore.web.interceptor.PagesViewsCountInterceptor;
import bg.softuni.webbookstore.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;
    private final PagesViewsCountInterceptor pagesViewsCountInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor, PagesViewsCountInterceptor pagesViewsCountInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.pagesViewsCountInterceptor = pagesViewsCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(pagesViewsCountInterceptor);
    }
}

package bg.softuni.webbookstore.web.interceptor;

import bg.softuni.webbookstore.service.StatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestsStatsInterceptor implements HandlerInterceptor {

    private final StatsService statsService;

    public RequestsStatsInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        statsService.onRequest();
        return true;
    }
}

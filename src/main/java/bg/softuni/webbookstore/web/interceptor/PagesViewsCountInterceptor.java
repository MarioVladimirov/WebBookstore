package bg.softuni.webbookstore.web.interceptor;

import bg.softuni.webbookstore.service.PagesCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PagesViewsCountInterceptor implements HandlerInterceptor {

    private final PagesCountService pagesCountService;

    public PagesViewsCountInterceptor(PagesCountService pagesCountService) {
        this.pagesCountService = pagesCountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        pagesCountService.onRequest(request.getRequestURI());
        return true;
    }
}

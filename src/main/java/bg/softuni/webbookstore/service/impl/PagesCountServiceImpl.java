package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.service.PagesCountService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class PagesCountServiceImpl implements PagesCountService {

    private Map<String, Integer> pagesViewsCount;

    public PagesCountServiceImpl() {
        pagesViewsCount = new HashMap<>();
    }

    @Override
    public void onRequest(String requestURI) {
        pagesViewsCount.put(requestURI, pagesViewsCount.getOrDefault(requestURI, 0) + 1);
    }

    @Override
    public Integer getPageViewsCount(String requestURI) {
        return pagesViewsCount.get(requestURI);
    }
}

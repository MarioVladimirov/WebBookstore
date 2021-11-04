package bg.softuni.webbookstore.service;

public interface PagesCountService {

    void onRequest(String requestURI);

    Integer getPageViewsCount(String requestURI);
}

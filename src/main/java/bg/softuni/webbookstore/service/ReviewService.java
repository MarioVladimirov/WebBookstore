package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.ReviewAddServiceModel;
import bg.softuni.webbookstore.model.view.ReviewViewModel;

import java.util.List;

public interface ReviewService {

    List<ReviewViewModel> findAllReviews();

    List<ReviewViewModel> findAllReviewsByUser(String username);

    Long add(ReviewAddServiceModel serviceModel);

    void deleteAllReviewsForBook(Long bookId);
}

package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.model.view.ReviewViewModel;
import bg.softuni.webbookstore.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ReviewViewModel>> getAllBooks() {

        List<ReviewViewModel> reviewViewModelsViewModels = reviewService.findAllReviews();

        return ResponseEntity
                .ok()
                .body(reviewViewModelsViewModels);
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<ReviewViewModel>> getAllBooksByUser(
            @AuthenticationPrincipal UserDetails principal) {

        List<ReviewViewModel> reviewViewModelsViewModels = reviewService
                .findAllReviewsByUser(principal.getUsername());

        return ResponseEntity
                .ok()
                .body(reviewViewModelsViewModels);
    }
}

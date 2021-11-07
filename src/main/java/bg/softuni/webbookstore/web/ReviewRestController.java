package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.ReviewViewModel;
import bg.softuni.webbookstore.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ReviewViewModel>> getAllReviewsByBook() {

        List<ReviewViewModel> reviewViewModels = reviewService.findAllReviews();

        return ResponseEntity
                .ok()
                .body(reviewViewModels);
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<ReviewViewModel>> getAllReviewsByUser(
            @AuthenticationPrincipal UserDetails principal) {

        List<ReviewViewModel> reviewViewModels = reviewService
                .findAllReviewsByUser(principal.getUsername());

        return ResponseEntity
                .ok()
                .body(reviewViewModels);
    }
}

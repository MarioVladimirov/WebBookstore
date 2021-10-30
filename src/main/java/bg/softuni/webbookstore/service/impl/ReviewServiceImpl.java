package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.ReviewEntity;
import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.service.ReviewAddServiceModel;
import bg.softuni.webbookstore.model.view.ReviewViewModel;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.repository.ReviewRepository;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReviewViewModel> findAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(this::getReviewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewViewModel> findAllReviewsByUser(String username) {
        return reviewRepository
                .findAllByAuthorUsername(username)
                .stream()
                .map(this::getReviewModel)
                .collect(Collectors.toList());
    }

    @Override
    public Long add(ReviewAddServiceModel serviceModel) {
        ReviewEntity reviewEntity = modelMapper
                .map(serviceModel, ReviewEntity.class)
                .setAuthor(getUserEntity(serviceModel.getAuthor()))
                .setBook(getBookEntity(serviceModel.getBookId()))
                .setAddedOn(Instant.now());

        ReviewEntity newEntity = reviewRepository.save(reviewEntity);

        return newEntity.getId();
    }

    private ReviewViewModel getReviewModel(ReviewEntity reviewEntity) {
        //TODO
        return modelMapper
                .map(reviewEntity, ReviewViewModel.class)
                .setAddedOn(reviewEntity.getAddedOn().atZone(ZoneId.systemDefault()));
    }

    private UserEntity getUserEntity(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Creator " + username + " could not be found")
                );
    }

    private BookEntity getBookEntity(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Book could not be found")
                );
    }
}

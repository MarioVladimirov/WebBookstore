package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.binding.BookUpdateBindingModel;
import bg.softuni.webbookstore.model.cloudinary.CloudinaryImage;
import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.service.BookUpdateServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.repository.*;
import bg.softuni.webbookstore.service.*;
import bg.softuni.webbookstore.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String DEFAULT_BOOK_IMAGE_URL = "/images/default-book-cover.png";
    private static final String DEFAULT_AUTHOR_IMAGE_URL = "/images/default-author-pic.png";

    private final BookRepository bookRepository;
    private final PictureRepository pictureRepository;
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final AuthorRepository authorRepository;
    private final CartItemRepository cartItemRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, PictureRepository pictureRepository, WishlistRepository wishlistRepository, UserRepository userRepository, CategoryRepository categoryRepository, PublishingHouseRepository publishingHouseRepository, AuthorRepository authorRepository, CartItemRepository cartItemRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.pictureRepository = pictureRepository;
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.authorRepository = authorRepository;
        this.cartItemRepository = cartItemRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookSummaryViewModel> findAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryViewModel> findBooksByAuthor(Long id) {
        return bookRepository
                .findByAuthorIdOrderByAddedOnDesc(id)
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryViewModel> findBooksByPublishingHouse(Long id) {
        return bookRepository
                .findByPublishingHouseIdOrderByAddedOnDesc(id)
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryViewModel> getWishListBooksByCustomer(String username) {
        return wishlistRepository
                .findAllByCustomerUsername(username)
                .stream()
                .map(wishlistItemEntity ->
                        getSummaryViewModel(wishlistItemEntity.getBook()))
                .collect(Collectors.toList());
    }

    @Override
    public String findBookTitleById(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Book not found"))
                .getTitle();
    }

    @Override
    public Long add(BookAddServiceModel bookAddServiceModel) throws IOException {

        MultipartFile img = bookAddServiceModel.getImage();

        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class)
                .setPicture(getPictureEntity(img))
                .setLanguage(getLanguageEnum(bookAddServiceModel.getLanguage()))
                .setCategories(getCategoryEntities(bookAddServiceModel.getCategories()))
                .setPublishingHouse(getPublishingHouseEntity(bookAddServiceModel.getPublishingHouse()))
                .setAuthor(getAuthorEntity(
                        bookAddServiceModel.getAuthorFirstName(),
                        bookAddServiceModel.getAuthorLastName()))
                .setCreator(getUserEntity(bookAddServiceModel.getCreator()));

        bookEntity = bookRepository.save(bookEntity);

        return bookEntity.getId();
    }

    @Override
    public Optional<BookDetailViewModel> findBookDetails(Long id) {
        return bookRepository
                .findById(id)
                .map(this::getBookDetailViewModel);
    }

    @Override
    public Optional<BookUpdateBindingModel> findBookToEdit(Long id) {
        return bookRepository
                .findById(id)
                .map(this::getBookToEdit);
    }

    @Override
    public Long update(BookUpdateServiceModel bookUpdateServiceModel) throws IOException {
        BookEntity bookEntity = bookRepository
                .findById(bookUpdateServiceModel.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found"));

        MultipartFile img = bookUpdateServiceModel.getImage();
        if (!"".equals(img.getOriginalFilename())) {
            deleteOldPicture(bookEntity.getId());
            bookEntity.setPicture(getPictureEntity(img));
        }

        bookEntity
                .setIsbn(bookUpdateServiceModel.getIsbn())
                .setDescription(bookUpdateServiceModel.getDescription())
                .setPagesCount(bookUpdateServiceModel.getPagesCount())
                .setCopies(bookUpdateServiceModel.getCopies())
                .setReleaseYear(bookUpdateServiceModel.getReleaseYear())
                .setPrice(bookUpdateServiceModel.getPrice())
                .setLanguage(getLanguageEnum(bookUpdateServiceModel.getLanguage()))
                .setCategories(getCategoryEntities(bookUpdateServiceModel.getCategories()))
                .setPublishingHouse(getPublishingHouseEntity(bookUpdateServiceModel.getPublishingHouseName()))
                .setAuthor(getAuthorEntity(
                        bookUpdateServiceModel.getAuthorFirstName(),
                        bookUpdateServiceModel.getAuthorLastName()));

        bookRepository.save(bookEntity);

        return bookUpdateServiceModel.getId();
    }

    @Override
    public void delete(Long id) {
        deleteOldPicture(id);
        bookRepository.deleteById(id);
    }

    private void deleteOldPicture(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalStateException("Book not found"));

        if (bookEntity.getPicture().getPublicId() != null) {
            cloudinaryService.deleteImage(bookEntity.getPicture().getPublicId());
        }
        Long pictureId = bookEntity.getPicture().getId();
        bookEntity.setPicture(null);
        bookRepository.save(bookEntity);
        if (!bookRepository.existsByPictureId(pictureId)) {
            pictureRepository.deleteById(pictureId);
        }
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    @Override
    public void increaseWithOneCopy(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Book not found"));

        bookEntity.setCopies(bookEntity.getCopies() + 1);
        bookRepository.save(bookEntity);
    }

    @Override
    public void decreaseWithOneCopy(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Book not found"));

        bookEntity.setCopies(bookEntity.getCopies() - 1);
        bookRepository.save(bookEntity);
    }


    private BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity) {
        return modelMapper
                .map(bookEntity, BookSummaryViewModel.class)
                .setCategories(StringUtils.getCategoriesAsStrings(bookEntity.getCategories()))
                .setAuthor(StringUtils.getFullNameAsString(
                        bookEntity.getAuthor().getFirstName(),
                        bookEntity.getAuthor().getLastName()))
                .setAuthorId(bookEntity.getAuthor().getId());
    }

    private BookDetailViewModel getBookDetailViewModel(BookEntity bookEntity) {
        return modelMapper
                .map(bookEntity, BookDetailViewModel.class)
                .setAddedOn(bookEntity.getAddedOn().atZone(ZoneId.systemDefault()))
                .setModified(bookEntity.getModified().atZone(ZoneId.systemDefault()))
                .setCategories(StringUtils.getCategoriesAsStrings(bookEntity.getCategories()))
                .setAuthor(StringUtils.getFullNameAsString(
                        bookEntity.getAuthor().getFirstName(),
                        bookEntity.getAuthor().getLastName()))
                .setAuthorId(bookEntity.getAuthor().getId())
                .setCreator(StringUtils.getFullNameAsString(
                        bookEntity.getCreator().getFirstName(),
                        bookEntity.getCreator().getLastName()));
    }

    private BookUpdateBindingModel getBookToEdit(BookEntity bookEntity) {
        return modelMapper
                .map(bookEntity, BookUpdateBindingModel.class)
                .setCategories(StringUtils.getCategoriesAsStrings(bookEntity.getCategories()));
    }

    private UserEntity getUserEntity(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Creator " + username + " could not be found")
                );
    }

    private AuthorEntity getAuthorEntity(String firstName, String lastName) {
        return authorRepository
                .findByFirstNameAndLastName(firstName, lastName)
                .orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity()
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setPicture(pictureRepository.save(new PictureEntity(DEFAULT_AUTHOR_IMAGE_URL)));
                    return authorRepository.save(newAuthor);
                });
    }

    private PublishingHouseEntity getPublishingHouseEntity(String publishingHouseName) {
        return publishingHouseRepository
                .findByName(publishingHouseName)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Publishing house " + publishingHouseName + " not found"
                ));
    }

    private LanguageEnum getLanguageEnum(String language) {
        return LanguageEnum.valueOf(language.toUpperCase());
    }

    private Set<CategoryEntity> getCategoryEntities(Set<String> categories) {
        return categories
                .stream()
                .map(category -> {
                    CategoryEnum categoryEnum = CategoryEnum.valueOf(
                            category.toUpperCase().replaceAll(" ", "_"));

                    return categoryRepository
                            .findByCategory(categoryEnum)
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Category with name " + category + " not found"
                            ));
                })
                .collect(Collectors.toSet());
    }

    private PictureEntity getPictureEntity(MultipartFile img) throws IOException {
        if (!"".equals(img.getOriginalFilename())) {
            final CloudinaryImage uploaded = cloudinaryService.uploadImage(img);
            return pictureRepository.save(new PictureEntity()
                    .setUrl(uploaded.getUrl())
                    .setPublicId(uploaded.getPublicId()));
        } else {
            return pictureRepository.save(new PictureEntity(DEFAULT_BOOK_IMAGE_URL));
        }
    }
}

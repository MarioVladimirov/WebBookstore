package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.binding.BookUpdateBindingModel;
import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.service.BookUpdateServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.repository.*;
import bg.softuni.webbookstore.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, CategoryRepository categoryRepository, PublishingHouseRepository publishingHouseRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.publishingHouseRepository = publishingHouseRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookSummaryViewModel> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryViewModel> findBooksByAuthor(Long id) {
        return bookRepository
                .findByAuthorId(id)
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryViewModel> findBooksByPublishingHouse(Long id) {
        return bookRepository
                .findByPublishingHouseId(id)
                .stream()
                .map(this::getSummaryViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Long update(BookUpdateServiceModel bookUpdateServiceModel) {
        BookEntity bookEntity = bookRepository
                .findById(bookUpdateServiceModel.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found"));

        bookEntity
                .setDescription(bookUpdateServiceModel.getDescription())
                .setImageUrl(bookUpdateServiceModel.getImageUrl())
                .setPagesCount(bookUpdateServiceModel.getPagesCount())
                .setCopies(bookUpdateServiceModel.getCopies())
                .setReleaseYear(bookUpdateServiceModel.getReleaseYear())
                .setPrice(bookUpdateServiceModel.getPrice())
                .setLanguage(getLanguageEnum(bookUpdateServiceModel.getLanguage()))
                .setCategories(getCategoryEntities(bookUpdateServiceModel.getCategories()))
                .setPublishingHouse(getPublishingHouseEntity(bookUpdateServiceModel.getPublishingHouseName()));

        bookRepository.save(bookEntity);

        return bookUpdateServiceModel.getId();
    }

    @Override
    public BookDetailViewModel findBookDetails(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookDetailViewModel viewModel = modelMapper
                .map(bookEntity, BookDetailViewModel.class);

        viewModel
                .setAddedOn(bookEntity.getAddedOn().atZone(ZoneId.systemDefault()))
                .setModified(bookEntity.getModified().atZone(ZoneId.systemDefault()))
                .setCategories(getCategoriesAsStrings(bookEntity.getCategories()))
                .setAuthor(getFullNameAsString(
                        bookEntity.getAuthor().getFirstName(), bookEntity.getAuthor().getLastName()))
                .setAuthorId(bookEntity.getAuthor().getId())
                .setCreator(getFullNameAsString(
                        bookEntity.getCreator().getFirstName(), bookEntity.getCreator().getLastName()));

        return viewModel;
    }

    @Override
    public BookUpdateBindingModel findBookToEdit(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookUpdateBindingModel updateBindingModel = modelMapper
                .map(bookEntity, BookUpdateBindingModel.class);

        updateBindingModel
                .setCategories(getCategoriesAsStrings(bookEntity.getCategories()))
                .setAuthor(getFullNameAsString(
                        bookEntity.getAuthor().getFirstName(), bookEntity.getAuthor().getLastName()));

        return updateBindingModel;
    }

    @Override
    public Long add(BookAddServiceModel bookAddServiceModel) {

        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);

        //TODO - set image url

        bookEntity
                .setLanguage(getLanguageEnum(bookAddServiceModel.getLanguage()))
                .setCategories(getCategoryEntities(bookAddServiceModel.getCategories()))
                .setPublishingHouse(getPublishingHouseEntity(bookAddServiceModel.getPublishingHouse()))
                .setAuthor(getAuthorEntity(
                        bookAddServiceModel.getAuthor().split(" ")[0],
                        bookAddServiceModel.getAuthor().split(" ")[1]))
                .setCreator(getUserEntity(bookAddServiceModel.getCreator()));

        bookEntity = bookRepository.save(bookEntity);

        return bookEntity.getId();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    private BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity) {
        BookSummaryViewModel viewModel = modelMapper
                .map(bookEntity, BookSummaryViewModel.class);

        viewModel
                .setCategories(getCategoriesAsStrings(bookEntity.getCategories()))
                .setAuthor(getFullNameAsString(
                        bookEntity.getAuthor().getFirstName(),
                        bookEntity.getAuthor().getLastName()));

        return viewModel;
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
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Author with name %s %s not found",
                                firstName, lastName)));
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
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (String category : categories) {
            CategoryEnum categoryEnum = CategoryEnum.valueOf(
                    category.toUpperCase().replaceAll(" ", "_"));
            CategoryEntity categoryEntity = categoryRepository
                    .findByCategory(categoryEnum)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Category with name " + category + " not found"
                    ));
            categoryEntities.add(categoryEntity);
        }
        return categoryEntities;
    }

    private Set<String> getCategoriesAsStrings(Set<CategoryEntity> categoryEntities) {
        Set<String> categoriesStr = new HashSet<>();
        categoryEntities
                .stream()
                .map(categoryEntity -> categoryEntity.getCategory().name())
                .map(s -> s.charAt(0) + s.substring(1).toLowerCase().replaceAll("_", " "))
                .forEach(categoriesStr::add);
        return categoriesStr;
    }

    private String getFullNameAsString(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

}

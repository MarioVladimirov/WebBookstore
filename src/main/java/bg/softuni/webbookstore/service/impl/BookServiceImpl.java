package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.repository.*;
import bg.softuni.webbookstore.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity) {
        BookSummaryViewModel viewModel = modelMapper
                .map(bookEntity, BookSummaryViewModel.class);

        Set<String> categories = new HashSet<>();
        bookEntity
                .getCategories()
                .stream()
                .map(categoryEntity -> categoryEntity.getCategory().name())
                .forEach(categories::add);

        viewModel
                .setCategories(categories)
                .setAuthor(
                        bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName());

        return viewModel;
    }

    @Override
    public BookDetailViewModel findById(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookDetailViewModel viewModel = modelMapper
                .map(bookEntity, BookDetailViewModel.class);

        Set<String> categories = new HashSet<>();
        bookEntity
                .getCategories()
                .stream()
                .map(categoryEntity -> categoryEntity.getCategory().name())
                .forEach(categories::add);

        viewModel
                .setCategories(categories)
                .setAuthor(
                        bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName())
                .setAuthorId(
                        bookEntity.getAuthor().getId());

        //TODO - check what is returned and if additional map is needed
        return viewModel;
    }

    @Override
    public Long add(BookAddServiceModel bookAddServiceModel) {

        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);

        //TODO - set image url

        bookEntity.setLanguage(
                LanguageEnum.valueOf(bookAddServiceModel.getLanguage().toUpperCase()));

        Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (String category : bookAddServiceModel.getCategories()) {
            CategoryEnum categoryEnum = CategoryEnum.valueOf(category.toUpperCase());
            CategoryEntity categoryEntity = categoryRepository
                    .findByCategory(categoryEnum)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Category with name " + category + " not found"
                    ));
            categoryEntities.add(categoryEntity);
        }
        bookEntity.setCategories(categoryEntities);

        PublishingHouseEntity publishingHouseEntity = publishingHouseRepository
                .findByName(bookAddServiceModel.getPublishingHouse())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Publishing house " + bookAddServiceModel.getPublishingHouse() + " not found"
                ));
        bookEntity.setPublishingHouse(publishingHouseEntity);

        AuthorEntity author = authorRepository
                .findByFirstNameAndLastName(
                        bookAddServiceModel.getAuthor().split(" ")[0],
                        bookAddServiceModel.getAuthor().split(" ")[1])
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Author with name %s %s not found",
                                bookAddServiceModel.getAuthor().split(" ")[0],
                                bookAddServiceModel.getAuthor().split(" ")[1]))
                );
        bookEntity.setAuthor(author);

        UserEntity creator = userRepository
                .findByUsername(bookAddServiceModel.getCreator())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Creator " + bookAddServiceModel.getCreator() + " could not be found")
                );
        bookEntity.setCreator(creator);


        bookEntity = bookRepository.save(bookEntity);

        return bookEntity.getId();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}

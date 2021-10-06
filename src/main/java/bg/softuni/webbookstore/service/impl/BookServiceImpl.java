package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.repository.*;
import bg.softuni.webbookstore.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(BookAddServiceModel bookAddServiceModel) {

        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);

        //TODO - set image url

        Set<CategoryEnum> categories = bookAddServiceModel.getCategoryEnums();
        for (CategoryEnum category : categories) {
            CategoryEntity categoryEntity = categoryRepository
                    .findByCategory(category)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Category with name " + category + " not found")
                    );
            bookEntity.getCategories().add(categoryEntity);
        }

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


        bookRepository.save(bookEntity);
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
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    @Override
    public BookDetailViewModel findById(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookDetailViewModel viewModel = modelMapper
                .map(bookEntity, BookDetailViewModel.class);

        //TODO - check what is returned and if additional map is needed
        return viewModel;
    }


    private BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity) {
        BookSummaryViewModel viewModel = modelMapper
                .map(bookEntity, BookSummaryViewModel.class);
        viewModel.setAuthor(
                bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName()
        );

        return viewModel;
    }
}

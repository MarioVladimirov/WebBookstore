package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewServiceModel;
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
    public List<BookViewServiceModel> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookViewServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}

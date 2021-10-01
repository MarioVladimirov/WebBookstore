package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewServiceModel;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, UserService userService, CategoryService categoryService, AuthorService authorService, PublishingHouseService publishingHouseService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void increaseCopies(String isbn, Integer copies) {
        BookEntity bookEntity = bookRepository
                .findByIsbn(isbn).get();
        bookEntity.increaseCopies(copies);

//        bookRepository.save(bookEntity);
    }

    @Override
    public void add(BookAddServiceModel bookAddServiceModel) {

        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);

        //TODO - set image url

        Set<CategoryEnum> categories = bookAddServiceModel.getCategoryEnums();
        for (CategoryEnum category : categories) {
            CategoryEntity categoryEntity = categoryService.findByCategoryName(category);
            bookEntity.getCategories().add(categoryEntity);
        }

        AuthorEntity author = authorService
                .findByName(bookAddServiceModel.getAuthor().split(" ")[0], bookAddServiceModel.getAuthor().split(" ")[1]);
        bookEntity.setAuthor(author);

        PublishingHouseEntity house = publishingHouseService
                .findByName(bookAddServiceModel.getPublishingHouse());
        bookEntity.setPublishingHouse(house);


        UserEntity creator = userService
                .findByUsername(bookAddServiceModel.getCreator())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + bookAddServiceModel.getCreator() + " could not be found"));
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
    public boolean existsByIsbn(BookAddBindingModel bookAddBindingModel) {
        return bookRepository
                .existsByIsbn(bookAddBindingModel.getIsbn());
    }
}
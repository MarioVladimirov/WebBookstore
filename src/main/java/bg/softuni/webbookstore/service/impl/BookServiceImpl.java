package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.binding.BookUpdateBindingModel;
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
    public BookDetailViewModel findBookDetails(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookDetailViewModel viewModel = modelMapper
                .map(bookEntity, BookDetailViewModel.class);

        viewModel
                .setAddedOn(bookEntity.getAddedOn().atZone(ZoneId.systemDefault()))
                .setModified(bookEntity.getModified().atZone(ZoneId.systemDefault()))
                .setCategories(getCategoriesAsStrings(bookEntity))
                .setAuthor(
                        bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName())
                .setAuthorId(
                        bookEntity.getAuthor().getId())
                .setCreator(
                        bookEntity.getCreator().getFirstName() + " " + bookEntity.getCreator().getLastName());

        //TODO - check what is returned and if additional map is needed
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
                .setCategories(getCategoriesAsStrings(bookEntity))
                .setAuthor(
                        bookEntity.getAuthor().getFirstName() + " " + bookEntity.getAuthor().getLastName());

        return updateBindingModel;
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
            CategoryEnum categoryEnum = CategoryEnum.valueOf(
                    category.toUpperCase().replaceAll(" ", "_"));
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

    private BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity) {
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

    private Set<String> getCategoriesAsStrings(BookEntity bookEntity) {
        Set<String> categories = new HashSet<>();
        bookEntity
                .getCategories()
                .stream()
                .map(categoryEntity -> categoryEntity.getCategory().name())
                .map(s -> s.charAt(0) + s.substring(1).toLowerCase().replaceAll("_", " "))
                .forEach(categories::add);
        return categories;
    }

}

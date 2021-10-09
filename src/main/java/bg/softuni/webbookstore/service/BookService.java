package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;

import java.util.List;

public interface BookService {


    List<BookSummaryViewModel> getAllBooks();

    BookSummaryViewModel getSummaryViewModel(BookEntity bookEntity);

    BookDetailViewModel findById(Long id);

    Long add(BookAddServiceModel bookAddServiceModel);

    boolean existsByIsbn(String isbn);
}

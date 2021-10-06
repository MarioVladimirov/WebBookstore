package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;

import java.util.List;

public interface BookService {

    void add(BookAddServiceModel bookAddServiceModel);

    List<BookSummaryViewModel> getAllBooks();

    boolean existsByIsbn(String isbn);

    BookDetailViewModel findById(Long id);
}

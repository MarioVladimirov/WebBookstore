package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;

import java.util.List;

public interface BookService {


    List<BookSummaryViewModel> getAllBooks();

    BookDetailViewModel findById(Long id);

    Long add(BookAddServiceModel bookAddServiceModel);

    boolean existsByIsbn(String isbn);

    List<BookSummaryViewModel> findBooksByAuthor(Long id);

    List<BookSummaryViewModel> findBooksByPublishingHouse(Long id);
}

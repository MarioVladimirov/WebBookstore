package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.binding.BookUpdateBindingModel;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;

import java.util.List;

public interface BookService {


    List<BookSummaryViewModel> getAllBooks();

    BookDetailViewModel findBookDetails(Long id);

    BookUpdateBindingModel findBookToEdit(Long id);

    Long add(BookAddServiceModel bookAddServiceModel);

    boolean existsByIsbn(String isbn);

    List<BookSummaryViewModel> findBooksByAuthor(Long id);

    List<BookSummaryViewModel> findBooksByPublishingHouse(Long id);

    void delete(Long id);


}

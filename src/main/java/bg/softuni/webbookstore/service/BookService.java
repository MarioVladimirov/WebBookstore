package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewModel;

import java.util.List;

public interface BookService {

    void add(BookAddServiceModel bookAddServiceModel);

    List<BookViewModel> getAllBooks();

    boolean existsByIsbn(String isbn);
}

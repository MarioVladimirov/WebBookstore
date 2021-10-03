package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewServiceModel;

import java.util.List;

public interface BookService {

    void add(BookAddServiceModel bookAddServiceModel);

    List<BookViewServiceModel> findAll();

    boolean existsByIsbn(String isbn);
}

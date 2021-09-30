package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewServiceModel;

import java.util.List;

public interface BookService {

    void increaseCopies(String isbn, Integer copies);

    void add(BookAddServiceModel bookAddServiceModel);

    List<BookViewServiceModel> findAll();

    boolean existsByIsbn(BookAddBindingModel bookAddBindingModel);
}

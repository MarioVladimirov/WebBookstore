package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.BookAddServiceModel;

public interface BookService {

    void increaseCopies(String isbn);

    void add(BookAddServiceModel bookAddServiceModel);

}

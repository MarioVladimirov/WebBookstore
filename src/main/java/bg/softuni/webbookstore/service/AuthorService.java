package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.AuthorAddServiceModel;
import bg.softuni.webbookstore.model.view.AuthorViewModel;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    List<String> findAllAuthorsNames();

    Long add(AuthorAddServiceModel authorAddServiceModel) throws IOException;

    AuthorViewModel findById(Long id);
}

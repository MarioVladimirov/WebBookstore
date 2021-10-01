package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.entity.AuthorEntity;

import java.util.List;

public interface AuthorService {

    List<String> findAllAuthorsNames();

    AuthorEntity findByName(String firstName, String lastName);
}

package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import bg.softuni.webbookstore.model.service.AuthorAddServiceModel;
import bg.softuni.webbookstore.repository.AuthorRepository;
import bg.softuni.webbookstore.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> findAllAuthorsNames() {
        return authorRepository
                .findAllAuthorsNames();
    }

    @Override
    public void add(AuthorAddServiceModel authorAddServiceModel) {

        AuthorEntity authorEntity = modelMapper
                .map(authorAddServiceModel, AuthorEntity.class);

        authorRepository.save(authorEntity);
    }
}

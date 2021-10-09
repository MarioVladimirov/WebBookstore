package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import bg.softuni.webbookstore.model.service.AuthorAddServiceModel;
import bg.softuni.webbookstore.model.view.AuthorViewModel;
import bg.softuni.webbookstore.repository.AuthorRepository;
import bg.softuni.webbookstore.service.AuthorService;
import bg.softuni.webbookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> findAllAuthorsNames() {
        return authorRepository
                .findAllAuthorsNames();
    }

    @Override
    public Long add(AuthorAddServiceModel authorAddServiceModel) {

        AuthorEntity authorEntity = modelMapper
                .map(authorAddServiceModel, AuthorEntity.class);

        authorEntity = authorRepository.save(authorEntity);

        return authorEntity.getId();
    }

    @Override
    public AuthorViewModel findById(Long id) {
        AuthorEntity authorEntity = authorRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not foudng"));

        AuthorViewModel viewModel = modelMapper
                .map(authorEntity, AuthorViewModel.class);

        viewModel.setBooks(
                authorEntity.getBooks()
                        .stream()
                        .map(bookService::getSummaryViewModel)
                        .collect(Collectors.toList())
        );

        return viewModel;
    }
}

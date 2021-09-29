package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void increaseCopies(String isbn) {

    }

    @Override
    public void add(BookAddServiceModel bookAddServiceModel) {



        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);



        bookRepository.save(bookEntity);
    }
}

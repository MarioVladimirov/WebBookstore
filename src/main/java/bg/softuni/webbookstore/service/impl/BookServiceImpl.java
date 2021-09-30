package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.view.BookViewServiceModel;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void increaseCopies(String isbn, Integer copies) {
        BookEntity bookEntity = bookRepository
                .findByIsbn(isbn).get();
        bookEntity.increaseCopies(copies);

//        bookRepository.save(bookEntity);
    }

    @Override
    public void add(BookAddServiceModel bookAddServiceModel) {



        BookEntity bookEntity = modelMapper
                .map(bookAddServiceModel, BookEntity.class);



        bookRepository.save(bookEntity);
    }

    @Override
    public List<BookViewServiceModel> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookViewServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIsbn(BookAddBindingModel bookAddBindingModel) {
        return bookRepository
                .existsByIsbn(bookAddBindingModel.getIsbn());
    }
}

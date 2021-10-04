package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookRestController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<BookSummaryViewModel>> getAllBooks() {

        List<BookSummaryViewModel> bookSummaryViewModels = bookService.getAllBooks();

        return ResponseEntity
                .ok()
                .body(bookSummaryViewModels);
    }

}

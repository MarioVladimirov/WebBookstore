package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("bookAddBindingModel")
    public BookAddBindingModel bookAddBindingModel() {
        return new BookAddBindingModel();
    }

    @GetMapping("/add")
    public String add() {
        return "add-book";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid BookAddBindingModel bookAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        //TODO
        //check if exists by ISBN -> bookService.increaseCopies(isbn);
        //do not require to fill all form fields; do not validate all other fields
        //else add as a new book

        //TODO
        //add validation


        BookAddServiceModel bookAddServiceModel = modelMapper
                .map(bookAddBindingModel, BookAddServiceModel.class);

        bookService.add(bookAddServiceModel);

        return "redirect:/home";
    }
}

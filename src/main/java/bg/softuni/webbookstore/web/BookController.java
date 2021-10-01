package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.service.AuthorService;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.PublishingHouseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, AuthorService authorService, PublishingHouseService publishingHouseService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("bookAddBindingModel")
    public BookAddBindingModel bookAddBindingModel() {
        return new BookAddBindingModel();
    }

    @GetMapping("/all")
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("categories", CategoryEnum.values());
        model.addAttribute("authors", authorService.findAllAuthorsNames());
        model.addAttribute("publishingHouses", publishingHouseService.findAllPublishingHousesNames());

        return "add-book";
    }

    @PostMapping("/edit")
    public String editBook() {

//        bookService.increaseCopies(bookAddBindingModel.getIsbn(), bookAddBindingModel.getCopies());

        return "redirect:/home";
        // TODO - create view and implement method
        // redirect to updated book details

    }

    @PostMapping("/add")
    public String addConfirm(@Valid BookAddBindingModel bookAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookAddBindingModel", bookAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookAddBindingModel", bindingResult);

            return "redirect:/books/add";
        }

        BookAddServiceModel bookAddServiceModel = modelMapper
                .map(bookAddBindingModel, BookAddServiceModel.class);

        bookAddServiceModel.setCreator(principal.getUsername());

        bookService.add(bookAddServiceModel);

        return "redirect:/home";
        // TODO
        // redirect to updated book details
    }
}

package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.BookAddBindingModel;
import bg.softuni.webbookstore.model.binding.BookUpdateBindingModel;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.service.BookAddServiceModel;
import bg.softuni.webbookstore.model.service.BookUpdateServiceModel;
import bg.softuni.webbookstore.model.view.BookDetailViewModel;
import bg.softuni.webbookstore.service.AuthorService;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.PublishingHouseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PublishingHouseService publishingHouseService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, PublishingHouseService publishingHouseService, AuthorService authorService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.publishingHouseService = publishingHouseService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("bookAddBindingModel")
    public BookAddBindingModel bookAddBindingModel() {
        return new BookAddBindingModel();
    }

    @ModelAttribute("bookUpdateBindingModel")
    public BookUpdateBindingModel bookUpdateBindingModel() {
        return new BookUpdateBindingModel();
    }

    //GET
    @GetMapping("/all")
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "home";
    }

    //CREATE
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("categories", CategoryEnum.values());
        model.addAttribute("publishingHouses", publishingHouseService.findAllPublishingHouseNames());
        return "add-book";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid BookAddBindingModel bookAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("bookAddBindingModel", bookAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookAddBindingModel", bindingResult);

            return "redirect:/books/add";
        }

        BookAddServiceModel bookAddServiceModel = modelMapper
                .map(bookAddBindingModel, BookAddServiceModel.class);

        bookAddServiceModel.setCreator(principal.getUsername());

        Long bookId = bookService.add(bookAddServiceModel);

        redirectAttributes.addFlashAttribute("addedSuccessfully", true);
        return "redirect:/books/" + bookId;
    }

    //DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable Long id,
                          Model model) {

        Optional<BookDetailViewModel> detailViewModel = bookService.findBookDetails(id);

        //TODO - error handling if empty optional
        model.addAttribute("book", detailViewModel.get());

        return "book-details";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model) {

        Optional<BookUpdateBindingModel> bookUpdateBindingModel = bookService.findBookToEdit(id);

        //TODO - error handling if empty optional
        model.addAttribute("bookUpdateBindingModel", bookUpdateBindingModel.get());
        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("categories", CategoryEnum.values());
        model.addAttribute("publishingHouses", publishingHouseService.findAllPublishingHouseNames());

        return "edit-book";
    }

    @GetMapping("/edit/{id}/errors")
    public String editConfirmErrors(@PathVariable Long id, Model model) {

        model.addAttribute("languages", LanguageEnum.values());
        model.addAttribute("categories", CategoryEnum.values());
        model.addAttribute("publishingHouses", publishingHouseService.findAllPublishingHouseNames());
        model.addAttribute("authors", authorService.findAllAuthorsNames());

        return "edit-book";
    }

    @PatchMapping("/edit/{id}")
    public String editConfirm(@PathVariable Long id,
                              @Valid BookUpdateBindingModel bookUpdateBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("bookUpdateBindingModel", bookUpdateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookUpdateBindingModel", bindingResult);

            return "redirect:/books/edit/" + id + "/errors";
        }

        BookUpdateServiceModel updateServiceModel = modelMapper
                .map(bookUpdateBindingModel, BookUpdateServiceModel.class);
        updateServiceModel.setId(id);

        bookService.update(updateServiceModel);

        redirectAttributes.addFlashAttribute("updatedSuccessfully", true);
        return "redirect:/books/" + id;
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);

        return "redirect:/books/all";
    }
}

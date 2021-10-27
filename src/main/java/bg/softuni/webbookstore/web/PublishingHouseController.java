package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.PublishingHouseViewModel;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.PublishingHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/publishinghouses")
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;
    private final BookService bookService;

    public PublishingHouseController(PublishingHouseService publishingHouseService, BookService bookService) {
        this.publishingHouseService = publishingHouseService;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id,
                          Model model) {

        Optional<PublishingHouseViewModel> viewModel = publishingHouseService.findById(id);

        //TODO - error handling if empty optional
        model.addAttribute("publishingHouse", viewModel.get());
        model.addAttribute("books", bookService.findBooksByPublishingHouse(id));

        return "publishing-house-details";
    }
}
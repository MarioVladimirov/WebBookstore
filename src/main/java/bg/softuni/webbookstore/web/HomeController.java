package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookService.findTopThreeNewestBooks());
        model.addAttribute("mostPopular", bookService.findTopThreeMostPopularBooks());
        return "index";
    }
}

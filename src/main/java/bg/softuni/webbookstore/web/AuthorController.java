package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.AuthorAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @ModelAttribute("authorAddBindingModel")
    public AuthorAddBindingModel authorAddBindingModel() {
        return new AuthorAddBindingModel();
    }

    @GetMapping("/add")
    public String add() {

        return "add-author";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AuthorAddBindingModel authorAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {


        return "redirect:/authors/details/";
    }
}

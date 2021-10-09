package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.AuthorAddBindingModel;
import bg.softuni.webbookstore.model.service.AuthorAddServiceModel;
import bg.softuni.webbookstore.model.view.AuthorViewModel;
import bg.softuni.webbookstore.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

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

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("authorAddBindingModel", authorAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorAddBindingModel", bindingResult);

            return "redirect:/authors/add";
        }

        AuthorAddServiceModel authorAddServiceModel = modelMapper
                .map(authorAddBindingModel, AuthorAddServiceModel.class);

        authorService.add(authorAddServiceModel);

        return "redirect:/authors/details/" + authorAddServiceModel.getId();
    }

    @GetMapping("/authors/details/{id}")
    public String details(@PathVariable Long id,
                          Model model) {

        AuthorViewModel viewModel = authorService.findById(id);

        model.addAttribute("author", viewModel);

        return "author-details";
    }

}

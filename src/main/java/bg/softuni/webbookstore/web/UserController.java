package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.UserLoginBindingModel;
import bg.softuni.webbookstore.model.binding.UserRegisterBindingModel;
import bg.softuni.webbookstore.model.service.UserLoginServiceModel;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;
import bg.softuni.webbookstore.model.view.BookSummaryViewModel;
import bg.softuni.webbookstore.model.view.UserViewModel;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, BookService bookService, ModelMapper modelMapper) {
        this.userService = userService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userRegisterServiceModel = modelMapper
                .map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.register(userRegisterServiceModel);

        return "redirect:/users/login";
    }

    @PostMapping("/login")
    public String loginConfirm(UserLoginBindingModel userLoginBindingModel) {

        userService.login(modelMapper
                .map(userLoginBindingModel, UserLoginServiceModel.class));

        return "redirect:/home";
    }

    // TODO - fix to work
    @GetMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes redirectAttributes) {

        redirectAttributes
                .addFlashAttribute("badCredentials", true)
                .addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model,
                              @AuthenticationPrincipal UserDetails principal) {

        Optional<UserViewModel> viewModel = userService
                .findByUsername(principal.getUsername());

        //TODO - error handling if empty optional
        model.addAttribute("user", viewModel.get());

        return "profile";
    }
}

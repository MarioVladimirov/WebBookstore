package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.binding.UserRegisterBindingModel;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;
import bg.softuni.webbookstore.service.UserService;
import com.sun.xml.bind.v2.TODO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }


    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerAndLoginUser(UserRegisterBindingModel userRegisterBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        //TODO
        //Add validation

        UserRegisterServiceModel userRegisterServiceModel = modelMapper
                .map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(userRegisterServiceModel);

        return "redirect:/home";
    }
}

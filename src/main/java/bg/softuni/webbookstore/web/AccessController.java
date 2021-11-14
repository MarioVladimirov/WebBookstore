package bg.softuni.webbookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "errors/unauthorized";
    }

}

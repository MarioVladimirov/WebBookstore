package bg.softuni.webbookstore.init;

import bg.softuni.webbookstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebBookstoreApplicationInit implements CommandLineRunner {

    private final UserService userService;

    public WebBookstoreApplicationInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
    }
}

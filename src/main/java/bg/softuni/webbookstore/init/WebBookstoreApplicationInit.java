package bg.softuni.webbookstore.init;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;
import bg.softuni.webbookstore.repository.AuthorRepository;
import bg.softuni.webbookstore.repository.PublishingHouseRepository;
import bg.softuni.webbookstore.service.CategoryService;
import bg.softuni.webbookstore.service.UserRoleService;
import bg.softuni.webbookstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebBookstoreApplicationInit implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;

    private final AuthorRepository authorRepository;
    private final PublishingHouseRepository publishingHouseRepository;

    public WebBookstoreApplicationInit(UserService userService, CategoryService categoryService, AuthorRepository authorRepository, PublishingHouseRepository publishingHouseRepository) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.authorRepository = authorRepository;
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        categoryService.seedCategories();

        //TODO
        //use services to initialize

        AuthorEntity author = new AuthorEntity().setFirstName("Test")
                .setLastName("Testov");
        authorRepository.save(author);

        PublishingHouseEntity house = new PublishingHouseEntity()
                .setName("House")
                .setAddress("Address")
                .setPhoneNumber("123")
                .setEmail("mail");
        publishingHouseRepository.save(house);

    }
}

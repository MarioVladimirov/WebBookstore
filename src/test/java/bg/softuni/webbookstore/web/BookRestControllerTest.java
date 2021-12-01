package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.entity.*;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.entity.enums.UserRoleEnum;
import bg.softuni.webbookstore.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser("testuser")
@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        userRoleRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        publishingHouseRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        initUserRole();
        initTestUser();
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        publishingHouseRepository.deleteAll();
        authorRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        initBooks();

        mockMvc
                .perform(get("/books/api/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].title", is("TestBook2")))
                .andExpect(jsonPath("$.[1].title", is("TestBook1")));
    }

    private void initUserRole() {
        UserRoleEntity userRole = new UserRoleEntity()
                .setRole(UserRoleEnum.USER);
        userRoleRepository.save(userRole);
    }

    private void initTestUser() {
        testUser = new UserEntity()
                .setFirstName("Test")
                .setLastName("Testov")
                .setUsername("testuser")
                .setEmail("test@test.bg")
                .setAddress("Sofia")
                .setPassword("12345");

        testUser = userRepository.save(testUser);
    }

    private void initBooks() {
        CategoryEntity testCategory = initCategory();
        PublishingHouseEntity testPublishingHouse = initPublishingHouse();
        AuthorEntity testAuthor = initAuthor();

        BookEntity book1 = new BookEntity()
                .setIsbn("1")
                .setTitle("TestBook1")
                .setAddedOn(Instant.now())
                .setModified(Instant.now())
                .setActive(true)
                .setPagesCount(100)
                .setCopies(1)
                .setReleaseYear(1000)
                .setPrice(BigDecimal.valueOf(10))
                .setLanguage(LanguageEnum.BULGARIAN)
                .setCategories(List.of(testCategory))
                .setPublishingHouse(testPublishingHouse)
                .setAuthor(testAuthor)
                .setCreator(testUser);

        BookEntity book2 = new BookEntity()
                .setIsbn("2")
                .setTitle("TestBook2")
                .setAddedOn(Instant.now())
                .setModified(Instant.now())
                .setActive(true)
                .setPagesCount(200)
                .setCopies(2)
                .setReleaseYear(2000)
                .setPrice(BigDecimal.valueOf(20))
                .setLanguage(LanguageEnum.BULGARIAN)
                .setCategories(List.of(testCategory))
                .setPublishingHouse(testPublishingHouse)
                .setAuthor(testAuthor)
                .setCreator(testUser);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    private CategoryEntity initCategory() {
        CategoryEntity testCategory = new CategoryEntity()
                .setCategory(CategoryEnum.FICTION);

        return categoryRepository.save(testCategory);
    }

    private PublishingHouseEntity initPublishingHouse() {
        PublishingHouseEntity testPublishingHouse = new PublishingHouseEntity()
                .setName("TestPublishingHouse");

        return publishingHouseRepository.save(testPublishingHouse);
    }

    private AuthorEntity initAuthor() {
        AuthorEntity testAuthor = new AuthorEntity()
                .setFirstName("TestAuthor")
                .setLastName("TestAuthor");

        return authorRepository.save(testAuthor);
    }
}
package bg.softuni.webbookstore.model.view;

import java.util.List;

public class AuthorViewModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;
    private List<BookSummaryViewModel> books;


    public AuthorViewModel() {
    }

    public Long getId() {
        return id;
    }

    public AuthorViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AuthorViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AuthorViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<BookSummaryViewModel> getBooks() {
        return books;
    }

    public AuthorViewModel setBooks(List<BookSummaryViewModel> books) {
        this.books = books;
        return this;
    }
}

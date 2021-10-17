package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.validator.PastOrCurrentYear;
import bg.softuni.webbookstore.model.validator.UniqueByIsbn;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class BookAddBindingModel {

    @UniqueByIsbn
    @NotBlank(message = "Please enter a valid isbn")
    @NotEmpty(message = "Please enter a valid ISBN number")
    private String isbn;

    @NotBlank(message = "Please enter a valid title")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters long")
    private String title;

    private String description;

    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))",
            message = "Please enter a valid image URL: 'https://{image path}.jpg' or 'https://{image path}.png'")
    private String imageUrl;

    @NotNull(message = "Pages count should be a positive number")
    @Positive(message = "Pages count should be a positive number")
    private Integer pagesCount;

    @NotNull(message = "Copies count should be a positive number")
    @Positive(message = "Copies count should be a positive number")
    private Integer copies;

    @PastOrCurrentYear
    private Integer releaseYear;

    @NotNull(message = "Price should be a positive number")
    @Positive(message = "Price should be a positive number")
    private BigDecimal price;

    @NotEmpty(message = "Please select language")
    private String language;

    @NotEmpty(message = "Please select categories")
    private Set<String> categories;

    @NotEmpty(message = "Please select publishing house")
    private String publishingHouse;

    @NotEmpty(message = "Please select author or create a new one")
    private String author;


    public BookAddBindingModel() {
    }

    public String getIsbn() {
        return isbn;
    }

    public BookAddBindingModel setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookAddBindingModel setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookAddBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookAddBindingModel setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookAddBindingModel setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public BookAddBindingModel setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public BookAddBindingModel setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookAddBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

}

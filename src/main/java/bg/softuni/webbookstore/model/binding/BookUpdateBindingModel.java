package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.validator.PastOrCurrentYear;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class BookUpdateBindingModel {

    private Long id;

    private String isbn;

    private String title;

    private String description;

//    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))",
//            message = "Please enter a valid image URL: 'https://{image path}.jpg' or 'https://{image path}.png'")
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
    private String publishingHouseName;

    private String author;


    public BookUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public BookUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookUpdateBindingModel setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookUpdateBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookUpdateBindingModel setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookUpdateBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookUpdateBindingModel setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookUpdateBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookUpdateBindingModel setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public BookUpdateBindingModel setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public BookUpdateBindingModel setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookUpdateBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

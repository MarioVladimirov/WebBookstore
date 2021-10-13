package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

public class BookUpdateBindingModel {

    private Long id;

    @NotEmpty(message = "Please enter a valid ISBN number")
    private String isbn;

    @NotEmpty(message = "Please enter a title")
    private String title;

    private String description;

    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))",
            message = "Please enter a valid image URL: 'https://{image path}.jpg' or 'https://{image path}.png'")
    private String imageUrl;

    @NotNull
    @Positive(message = "Pages count should be a positive number")
    private Integer pagesCount;

    @NotNull
    @Positive(message = "Copies count should be a positive number")
    private Integer copies;

    @NotNull(message = "Please enter the book's release year.")
    //TODO - annotation to check that year is past or present
    private Integer releaseYear;

    @NotNull
    @Positive(message = "Price should be a positive number")
    private BigDecimal price;

    @NotNull
    private LanguageEnum language;

    @NotNull
    private Set<String> categories;

    @NotEmpty
    private String publishingHouseName;

    @NotEmpty
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

    public LanguageEnum getLanguage() {
        return language;
    }

    public BookUpdateBindingModel setLanguage(LanguageEnum language) {
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

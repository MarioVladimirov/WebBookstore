package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class BookAddBindingModel {

    @NotEmpty(message = "Please enter a valid ISBN number")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
            message = "Please enter a valid ISBN number")
    private String isbn;

    @NotEmpty(message = "Please enter a title")
    private String title;

    private String description;

    private String imageUrl;

    @Positive(message = "Pages count should be a positive number")
    private Integer pagesCount;

    @Positive(message = "Copies count should be a positive number")
    private Integer copies;

    @NotNull(message = "Please enter the book's release year.")
    //TODO - annotation to check that year is past or present
    private Integer releaseYear;

    @Positive(message = "Price should be a positive number")
    private BigDecimal price;

    @NotNull
    private LanguageEnum language;

    @NotNull
    private Set<CategoryEnum> categoryEnums;

    @NotNull
    private String author;

    @NotNull
    private String publishingHouse;


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

    public LanguageEnum getLanguage() {
        return language;
    }

    public BookAddBindingModel setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }

    public Set<CategoryEnum> getCategoryEnums() {
        return categoryEnums;
    }

    public BookAddBindingModel setCategoryEnums(Set<CategoryEnum> categoryEnums) {
        this.categoryEnums = categoryEnums;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookAddBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public BookAddBindingModel setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }
}
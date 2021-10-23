package bg.softuni.webbookstore.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

public class BookAddServiceModel {

    private String isbn;
    private String title;
    private String description;
    private MultipartFile image;
    private Integer pagesCount;
    private Integer copies;
    private Integer releaseYear;
    private BigDecimal price;
    private String language;
    private Set<String> categories;
    private String publishingHouse;
    private String author;
    private String creator;


    public BookAddServiceModel() {
    }

    public String getIsbn() {
        return isbn;
    }

    public BookAddServiceModel setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BookAddServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookAddServiceModel setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookAddServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookAddServiceModel setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookAddServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookAddServiceModel setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public BookAddServiceModel setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public BookAddServiceModel setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public BookAddServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

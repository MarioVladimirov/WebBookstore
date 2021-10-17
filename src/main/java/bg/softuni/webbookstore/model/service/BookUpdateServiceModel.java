package bg.softuni.webbookstore.model.service;

import java.math.BigDecimal;
import java.util.Set;

public class BookUpdateServiceModel {

    private Long id;
    private String description;
    private String imageUrl;
    private Integer pagesCount;
    private Integer copies;
    private Integer releaseYear;
    private BigDecimal price;
    private String language;
    private Set<String> categories;
    private String publishingHouseName;


    public BookUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public BookUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookUpdateServiceModel setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookUpdateServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookUpdateServiceModel setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookUpdateServiceModel setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public BookUpdateServiceModel setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public BookUpdateServiceModel setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
        return this;
    }
}

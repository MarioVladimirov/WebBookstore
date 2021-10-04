package bg.softuni.webbookstore.model.view;

import bg.softuni.webbookstore.model.entity.CategoryEntity;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;
import bg.softuni.webbookstore.model.entity.enums.PublishingHouseEnum;

import java.math.BigDecimal;
import java.util.Set;

public class BookDetailViewModel {

    private String title;
    private String description;
    private String imageUrl;
    private Integer pagesCount;
    private Integer copies;
    private Integer releaseYear;
    private BigDecimal price;
    private LanguageEnum language;
    private PublishingHouseEnum publishingHouse;
    private Set<CategoryEntity> categories;
    private String author;

    public BookDetailViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public BookDetailViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookDetailViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookDetailViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookDetailViewModel setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookDetailViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookDetailViewModel setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookDetailViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public BookDetailViewModel setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }

    public PublishingHouseEnum getPublishingHouse() {
        return publishingHouse;
    }

    public BookDetailViewModel setPublishingHouse(PublishingHouseEnum publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public BookDetailViewModel setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDetailViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

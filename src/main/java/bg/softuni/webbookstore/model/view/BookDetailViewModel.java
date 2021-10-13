package bg.softuni.webbookstore.model.view;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class BookDetailViewModel {

    private Long id;
    private String isbn;
    private String title;
    private String description;
    private String imageUrl;
    private Instant addedOn;
    private Integer pagesCount;
    private Integer copies;
    private Integer releaseYear;
    private BigDecimal price;
    private LanguageEnum language;
    private Set<String> categories;
    private PublishingHouseViewModel publishingHouse;
    private String author;
    private Long authorId;
    private String creator;

    public BookDetailViewModel() {
    }

    public Long getId() {
        return id;
    }

    public BookDetailViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDetailViewModel setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
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

    public Instant getAddedOn() {
        return addedOn;
    }

    public BookDetailViewModel setAddedOn(Instant addedOn) {
        this.addedOn = addedOn;
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

    public Set<String> getCategories() {
        return categories;
    }

    public BookDetailViewModel setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public PublishingHouseViewModel getPublishingHouse() {
        return publishingHouse;
    }

    public BookDetailViewModel setPublishingHouse(PublishingHouseViewModel publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDetailViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public BookDetailViewModel setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public BookDetailViewModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}

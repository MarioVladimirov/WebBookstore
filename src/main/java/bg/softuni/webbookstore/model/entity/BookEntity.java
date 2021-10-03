package bg.softuni.webbookstore.model.entity;

import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "added_on", nullable = false)
    private Instant addedOn;

    @Column(name = "pages_count", nullable = false)
    private Integer pagesCount;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToOne(optional = false)
    private AuthorEntity author;

    @ManyToOne(optional = false)
    private PublishingHouseEntity publishingHouse;

    @ManyToOne(optional = false)
    private UserEntity creator;


    public String getIsbn() {
        return isbn;
    }

    public BookEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Instant getAddedOn() {
        return addedOn;
    }

    public BookEntity setAddedOn(Instant addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public BookEntity setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public BookEntity setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BookEntity setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public BookEntity setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public BookEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public BookEntity setPublishingHouse(PublishingHouseEntity publishingHouse) {
        this.publishingHouse = publishingHouse;
        return this;
    }

    @PrePersist
    public void afterAdd() {
        setAddedOn(Instant.now());
    }

    public BookEntity increaseCopies(Integer copies) {
        this.copies += copies;
        return this;
    }

    public BookEntity decreaseCopies(Integer copies) {
        this.copies -= copies;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public BookEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }
}

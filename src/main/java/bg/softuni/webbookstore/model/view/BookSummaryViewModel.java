package bg.softuni.webbookstore.model.view;

import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;

import java.math.BigDecimal;
import java.util.Set;

public class BookSummaryViewModel {

    private Long id;
    private String title;
    private String imageUrl;
    private BigDecimal price;
    private Set<CategoryEnum> categories;
    private String author;


    public BookSummaryViewModel() {
    }

    public Long getId() {
        return id;
    }

    public BookSummaryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookSummaryViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookSummaryViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookSummaryViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public BookSummaryViewModel setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookSummaryViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

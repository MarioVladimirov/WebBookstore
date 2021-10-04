package bg.softuni.webbookstore.model.view;

import java.math.BigDecimal;

public class BookSummaryViewModel {

    private String title;
    private String imageUrl;
    private BigDecimal price;
    private String author;


    public BookSummaryViewModel() {
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

    public String getAuthor() {
        return author;
    }

    public BookSummaryViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

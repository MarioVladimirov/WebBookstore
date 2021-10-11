package bg.softuni.webbookstore.model.view;

import java.math.BigDecimal;

public class BookCartItemViewModel {

    private Long id;
    private String title;
    private String imageUrl;
    private BigDecimal price;


    public BookCartItemViewModel() {
    }

    public Long getId() {
        return id;
    }

    public BookCartItemViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookCartItemViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookCartItemViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookCartItemViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

}

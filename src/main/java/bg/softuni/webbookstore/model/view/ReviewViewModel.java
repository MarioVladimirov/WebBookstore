package bg.softuni.webbookstore.model.view;

import java.time.ZonedDateTime;

public class ReviewViewModel {

    private String nickname;
    private String title;
    private String textContent;
    private Integer rating;
    private ZonedDateTime addedOn;
    private String bookTitle;


    public ReviewViewModel() {
    }

    public String getNickname() {
        return nickname;
    }

    public ReviewViewModel setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ReviewViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public ReviewViewModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public ReviewViewModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public ZonedDateTime getAddedOn() {
        return addedOn;
    }

    public ReviewViewModel setAddedOn(ZonedDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public ReviewViewModel setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }
}

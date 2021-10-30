package bg.softuni.webbookstore.model.view;

import java.time.Instant;

public class ReviewViewModel {

    private String nickname;
    private String title;
    private String textContent;
    private Integer rating;
    private Instant addedOn;
    private String bookTitle;
    private Long bookId;


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

    public Instant getAddedOn() {
        return addedOn;
    }

    public ReviewViewModel setAddedOn(Instant addedOn) {
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

    public Long getBookId() {
        return bookId;
    }

    public ReviewViewModel setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }
}

package bg.softuni.webbookstore.model.service;

public class ReviewAddServiceModel {

    private Long bookId;
    private String nickname;
    private String title;
    private String textContent;
    private Integer rating;
    private String author;

    public ReviewAddServiceModel() {
    }

    public Long getBookId() {
        return bookId;
    }

    public ReviewAddServiceModel setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public ReviewAddServiceModel setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ReviewAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public ReviewAddServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public ReviewAddServiceModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ReviewAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}

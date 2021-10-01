package bg.softuni.webbookstore.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publishing_houses")
public class PublishingHouseEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    private String webSite;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.EAGER)
    private List<BookEntity> books;

    public String getName() {
        return name;
    }

    public PublishingHouseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PublishingHouseEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PublishingHouseEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PublishingHouseEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWebSite() {
        return webSite;
    }

    public PublishingHouseEntity setWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PublishingHouseEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public PublishingHouseEntity setBooks(List<BookEntity> books) {
        this.books = books;
        return this;
    }
}

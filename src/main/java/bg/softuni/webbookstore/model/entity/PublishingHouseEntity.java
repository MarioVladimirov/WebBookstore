package bg.softuni.webbookstore.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publishing_houses")
public class PublishingHouseEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.EAGER)
    private List<BookEntity> books;


    public PublishingHouseEntity() {
    }

    public String getName() {
        return name;
    }

    public PublishingHouseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PublishingHouseEntity setDescription(String description) {
        this.description = description;
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

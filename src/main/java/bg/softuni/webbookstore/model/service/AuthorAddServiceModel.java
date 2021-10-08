package bg.softuni.webbookstore.model.service;

public class AuthorAddServiceModel {

    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;


    public AuthorAddServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorAddServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorAddServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AuthorAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AuthorAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

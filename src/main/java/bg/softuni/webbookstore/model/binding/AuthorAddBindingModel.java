package bg.softuni.webbookstore.model.binding;

import javax.validation.constraints.Size;

public class AuthorAddBindingModel {

    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters long")
    private String lastName;

    private String description;

    private String imageUrl;


    public AuthorAddBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorAddBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorAddBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AuthorAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AuthorAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

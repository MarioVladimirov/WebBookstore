package bg.softuni.webbookstore.model.view;

public class AuthorViewModel {

    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

    public AuthorViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AuthorViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AuthorViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

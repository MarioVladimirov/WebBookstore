package bg.softuni.webbookstore.model.binding;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String imageUrl;

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserRegisterBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

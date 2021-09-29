package bg.softuni.webbookstore.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotEmpty(message = "Please enter your first name")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long")
    private String firstName;

    @NotEmpty(message = "Please enter your last name")
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters long")
    private String lastName;

    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters long")
    @NotEmpty(message = "Please enter a username")
    private String username;

    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))",
            message = "Please enter a valid image URL: 'https://{image path}.jpg' or 'https://{image path}.png'")
    private String imageUrl;

    @Size(min = 2, max = 20, message = "Password should be between 2 and 20 characters long")
    @NotEmpty(message = "Please enter a password")
    private String password;

    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;


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

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserRegisterBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

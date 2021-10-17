package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.validator.PasswordsMatch;
import bg.softuni.webbookstore.model.validator.UniqueUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordsMatch(
        password = "password",
        confirmPassword = "confirmPassword"
)
public class UserRegisterBindingModel {

    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters long")
    private String lastName;

    @UniqueUsername
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters long")
    private String username;

    @Size(min = 2, max = 20, message = "Password should be between 2 and 20 characters long")
    private String password;

    @Size(min = 2, max = 20, message = "Password should be between 2 and 20 characters long")
    private String confirmPassword;


    public UserRegisterBindingModel() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

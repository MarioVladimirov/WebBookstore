package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean userNameExists(String username);
}

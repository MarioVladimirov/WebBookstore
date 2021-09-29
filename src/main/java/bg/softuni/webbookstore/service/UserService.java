package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

public interface UserService {

    void seedUsers();

    void register(UserRegisterServiceModel userRegisterServiceModel);

    boolean userNameExists(String username);
}

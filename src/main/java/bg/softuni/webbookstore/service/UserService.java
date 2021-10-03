package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel userRegisterServiceModel);

    boolean userNameExists(String username);

}

package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.UserLoginServiceModel;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel userRegisterServiceModel);

    void login(UserLoginServiceModel loginServiceModel);

    boolean userNameExists(String username);

}

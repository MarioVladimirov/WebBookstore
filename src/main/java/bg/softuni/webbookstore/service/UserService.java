package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

public interface UserService {
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
}

package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {

    void seedUsers();

    void register(UserRegisterServiceModel userRegisterServiceModel);

    boolean userNameExists(String username);

    Optional<UserEntity> findByUsername(String username);
}

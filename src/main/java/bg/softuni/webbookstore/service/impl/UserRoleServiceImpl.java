package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.UserRoleEntity;
import bg.softuni.webbookstore.model.entity.enums.UserRoleEnum;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.repository.UserRoleRepository;
import bg.softuni.webbookstore.service.UserRoleService;
import bg.softuni.webbookstore.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void assignUserRole(String username, UserRoleEnum roleEnum) {
        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        UserRoleEntity roleEntity = userRoleRepository
                .findByRole(roleEnum)
                .orElseThrow(() ->
                        new ObjectNotFoundException("user role"));

        userEntity.addRole(roleEntity);
        userRepository.save(userEntity);
    }
}

package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.UserRoleEntity;
import bg.softuni.webbookstore.model.entity.enums.UserRoleEnum;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.repository.UserRoleRepository;
import bg.softuni.webbookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final WebBookstoreUserService webBookstoreUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, WebBookstoreUserService webBookstoreUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.webBookstoreUserService = webBookstoreUserService;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setPassword(passwordEncoder.encode("123"))
                    .setRoles(List.of(adminRole, userRole));

            UserEntity user = new UserEntity()
                    .setUsername("user")
                    .setFirstName("User")
                    .setLastName("Userov")
                    .setPassword(passwordEncoder.encode("123"))
                    .setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity newUser = modelMapper
                .map(userRegisterServiceModel, UserEntity.class);

        newUser.setPassword(passwordEncoder
                .encode(userRegisterServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(
                        () -> new IllegalStateException("USER role not found!")
                );
        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = webBookstoreUserService
                .loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

    }
}

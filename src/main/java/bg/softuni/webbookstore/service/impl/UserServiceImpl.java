package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.UserRoleEntity;
import bg.softuni.webbookstore.model.entity.enums.UserRoleEnum;
import bg.softuni.webbookstore.model.service.UserLoginServiceModel;
import bg.softuni.webbookstore.model.service.UserRegisterServiceModel;
import bg.softuni.webbookstore.model.view.UserViewModel;
import bg.softuni.webbookstore.repository.BookRepository;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final BookstoreUserService bookstoreUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, BookstoreUserService bookstoreUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.bookstoreUserService = bookstoreUserService;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
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

        userRepository.save(newUser);
    }

    @Override
    public void login(UserLoginServiceModel loginServiceModel) {
        UserDetails principal = bookstoreUserService
                .loadUserByUsername(loginServiceModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                loginServiceModel.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .isPresent();
    }

    @Override
    public Optional<UserViewModel> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(userEntity -> modelMapper
                        .map(userEntity, UserViewModel.class));
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository.findAllUsernames();
    }
}

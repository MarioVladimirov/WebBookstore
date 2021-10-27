package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.WishlistItemEntity;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.repository.WishlistRepository;
import bg.softuni.webbookstore.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, BookRepository bookRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addToWishlist(Long id, String username) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Book not found"));

        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));

        WishlistItemEntity wishlistItemEntity = new WishlistItemEntity()
                .setBook(bookEntity)
                .setCustomer(userEntity);

        wishlistRepository.save(wishlistItemEntity);
    }

    @Override
    public void removeFromWishlist(Long id, String username) {
        WishlistItemEntity wishlistItemEntity = wishlistRepository
                .findByBookIdAndCustomerUsername(id, username)
                .orElseThrow(() ->
                        new IllegalStateException("Book not found"));

        wishlistRepository
                .deleteById(wishlistItemEntity.getId());
    }
}

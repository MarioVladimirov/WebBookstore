package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.CartItemEntity;
import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.WishlistItemEntity;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.repository.WishlistRepository;
import bg.softuni.webbookstore.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<WishlistItemEntity> wishlistItem = wishlistRepository
                .findByBookIdAndCustomerUsername(id, username);

        if (wishlistItem.isEmpty()) {
            BookEntity book = bookRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new IllegalStateException("Book not found"));

            UserEntity customer = userRepository
                    .findByUsername(username)
                    .orElseThrow(() ->
                            new IllegalStateException("User not found"));

            WishlistItemEntity wishlistItemEntity = new WishlistItemEntity()
                    .setBook(book)
                    .setCustomer(customer);

            wishlistRepository.save(wishlistItemEntity);
        }
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

    @Override
    public void deleteFromAllWishlists(Long bookId) {
        wishlistRepository
                .deleteAll(wishlistRepository
                        .findAllByBookId(bookId));
    }
}

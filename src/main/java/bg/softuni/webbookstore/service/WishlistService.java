package bg.softuni.webbookstore.service;

public interface WishlistService {

    void addToWishlist(Long id, String username);

    void removeFromWishlist(Long id, String username);

    void deleteFromAllWishlists(Long bookId);
}

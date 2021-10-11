package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findByCustomerId(Long id);

    List<CartItemEntity> findByCustomerUsername(String username);
}

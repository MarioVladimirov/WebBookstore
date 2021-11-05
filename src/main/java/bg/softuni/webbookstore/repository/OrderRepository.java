package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerUsernameOrderByOrderTimeDesc(String customer_username);

    List<OrderEntity> findTop5ByCustomerUsernameOrderByOrderTimeDesc(String username);

    Optional<OrderEntity> findTopByOrderByOrderTimeDesc();
}

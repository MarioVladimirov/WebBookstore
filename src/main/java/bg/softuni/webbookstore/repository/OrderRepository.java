package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findTop5ByCustomerUsernameOrderByDateDesc(String username);
}

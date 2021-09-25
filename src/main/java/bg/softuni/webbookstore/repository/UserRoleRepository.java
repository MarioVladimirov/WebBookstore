package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}

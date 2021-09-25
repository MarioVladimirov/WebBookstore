package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.BookEntity;
import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouseEntity, Long> {
}

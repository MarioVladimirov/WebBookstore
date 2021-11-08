package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIdAndActiveTrue(Long id);

    boolean existsByIsbn(String isbn);

    boolean existsByPictureId(Long pictureId);

    List<BookEntity> findAllByActiveTrueOrderByAddedOnDesc();

    List<BookEntity> findTop3ByActiveTrueOrderByAddedOnDesc();

    List<BookEntity> findAllByActiveTrueAndAuthorIdOrderByAddedOnDesc(Long id);

    List<BookEntity> findAllByActiveTrueAndPublishingHouseIdOrderByAddedOnDesc(Long id);
}

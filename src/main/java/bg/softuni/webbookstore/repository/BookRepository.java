package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT b FROM BookEntity b " +
            "LEFT JOIN OrderItemEntity o ON b.id = o.book.id " +
            "LEFT JOIN ReviewEntity r ON b.id = r.book.id " +
            "GROUP BY b.id " +
            "ORDER BY COUNT(o.id) DESC, COUNT(r.id) DESC, b.addedOn DESC")
    List<BookEntity> findTop3MostPopularBySoldCopiesAndReviewsCount(Pageable pageable);

    List<BookEntity> findAllByActiveTrueAndAuthorIdOrderByAddedOnDesc(Long id);

    List<BookEntity> findAllByActiveTrueAndPublishingHouseIdOrderByAddedOnDesc(Long id);
}

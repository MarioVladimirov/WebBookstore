package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByBookId(Long bookId);

    List<ReviewEntity> findAllByAuthorUsername(String username);

    @Transactional
    void deleteAllByBookId(Long bookId);
}

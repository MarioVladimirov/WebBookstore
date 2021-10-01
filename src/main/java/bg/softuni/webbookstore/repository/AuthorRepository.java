package bg.softuni.webbookstore.repository;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName) FROM AuthorEntity a " +
            "ORDER BY a.firstName")
    List<String> findAllAuthorsNames();

    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}

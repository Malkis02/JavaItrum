package itrum.task2.repositories;

import itrum.task2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}

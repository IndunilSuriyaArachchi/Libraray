package pronto.java.task.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pronto.java.task.library.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>{

}

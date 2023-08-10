package pronto.java.task.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pronto.java.task.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	
	@Query("select b from Book b where b.title =:title")
	public List<Book> getBookByName(@Param("title") String title);
	
	@Query("select distinct b from Book b left join fetch b.authors a where authorName =:authorName")
	public List<Book> getBookByAuthor(@Param("authorName") String authorName);
	
}

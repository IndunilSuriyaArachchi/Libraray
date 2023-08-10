package pronto.java.task.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pronto.java.task.library.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("from User where userName =:userName")
	User findByUsername(@Param("userName") String userName);

}

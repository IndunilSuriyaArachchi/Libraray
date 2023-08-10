package pronto.java.task.library.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pronto.java.task.library.model.User;
import pronto.java.task.library.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	@Query("select ln from Loan ln where ln.pending =:pending")
	public List<Loan> getAllActiveLoans(@Param("pending") boolean pending);
	
	@Query("from Loan where user =:user")
	public List<Loan> getAllLoansByUser(@Param("user") User user);
	
	@Query("from Loan where user =:user and pending =:pending")
	public List<Loan> getAllActiveLoansByUser(@Param("user") User user, @Param("pending") boolean pending);


}

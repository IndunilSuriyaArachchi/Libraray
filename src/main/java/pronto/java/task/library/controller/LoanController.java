package pronto.java.task.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pronto.java.task.library.model.Loan;
import pronto.java.task.library.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {
	public static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	private LoanService loanService;
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@GetMapping({"/list"})
	public List<Loan> getAllLoans() {
		return loanService.getAllLoans();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@GetMapping({"/filterbyuser/{userid}"})
	public List<Loan> getAllLoansByUserId(@PathVariable Long userid) {
		return loanService.getAllLoansByUser(userid);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@GetMapping({"/filteractiveuserloan/{userid}"})
	public List<Loan> getAllActiveLoansByUser(@PathVariable Long userid) {
		return loanService.getAllActiveLoansByUser(userid);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@GetMapping({"/filteractiveloan"})
	public List<Loan> getAllLoansByActiveLoans() {
		return loanService.getAllLoans();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody List<Loan> loan) {
		
		List<Loan> savedloan=null;
		try {
			savedloan=loanService.saveAll(loan);
			return ResponseEntity.ok(savedloan);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("ERROR", HttpStatus.BAD_REQUEST);
		}
	}
}

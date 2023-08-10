package pronto.java.task.library.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import pronto.java.task.library.model.User;
import pronto.java.task.library.model.Book;
import pronto.java.task.library.model.Loan;
import pronto.java.task.library.repo.BookRepository;
import pronto.java.task.library.repo.LoanRepository;
import pronto.java.task.library.repo.UserRepository;
import pronto.java.task.library.websocket.ServerWebSocketHandler;

@Service
public class LoanService {
	
	
	@Autowired
	private LoanRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ServerWebSocketHandler socketHandler;
	
	
	public List<Loan> getAllLoans(){
		return repository.findAll();
		
	}
	
	public List<Loan> getAllLoansByUser(Long userid){
		User user=userRepository.getById(userid);
		return repository.getAllLoansByUser(user);
		
	}
	
	public List<Loan> getAllActiveLoansByUser(Long userid){
		User user=new User();
		user.setUserId(userid);
		boolean pending=true;
		return FineCalculatorService.applyFineCalculation(repository.getAllActiveLoansByUser(user, pending));
		
	}
	
	public List<Loan> getAllActiveLoans(){
		boolean pending=true;
		return FineCalculatorService.applyFineCalculation(repository.getAllActiveLoans(pending));
		
	}
	
	
	public List<Loan> saveAll(List<Loan> userBookList) {
		List<Book> bookList=new ArrayList();
		
		List<Loan> savedLoanList=repository.saveAll(userBookList);
		Book book;
		for (Loan loan : savedLoanList) {
			book=bookRepository.getById(loan.getBook().getId());
			if(loan.isPending()) {
				book.setAvailable(book.getAvailable()!= 0 ? book.getAvailable()-1 : 0);
			}else {
				book.setAvailable(book.getAvailable()!= 0 ? book.getAvailable()+1 : 0);		
				StringBuilder authorNames = new StringBuilder();
				book.getAuthors().stream().forEach(author -> {
					authorNames.append(author.getAuthorName()+":");
			    });
				String message="Returned Book: "+book.getTitle()+ "  By Author: "+ authorNames.toString()+"  Available Count:"+book.getAvailable();
				try {
					socketHandler.handleTextMessage( new TextMessage(message));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			bookList.add(book);
		}
		
		bookRepository.saveAll(bookList);

		return savedLoanList;
	}

}

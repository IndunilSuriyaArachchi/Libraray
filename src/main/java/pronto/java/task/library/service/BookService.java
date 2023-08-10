package pronto.java.task.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import pronto.java.task.library.websocket.ServerWebSocketHandler;

import pronto.java.task.library.model.Book;
import pronto.java.task.library.repo.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private ServerWebSocketHandler socketHandler;
	
	public List<Book> getAllBooks(){
		return repository.findAll();
		
	}
	
	public Book save(Book book) {
		Book savedBook=repository.save(book);
		StringBuilder authorNames = new StringBuilder();
		savedBook.getAuthors().stream().forEach(author -> {
			authorNames.append(author.getAuthorName()+":");
	    });
		
		String message="New Book Arrived: "+savedBook.getTitle()+ "  By Author: "+ authorNames.toString()+"  Available Count:"+savedBook.getAvailable();
		try {
			socketHandler.handleTextMessage( new TextMessage(message));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedBook;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Book> getBookByName(String name) {
		return repository.getBookByName(name);
	}
	
	public List<Book> getBookByAuthor(String name) {
		return repository.getBookByAuthor(name);
	}
}

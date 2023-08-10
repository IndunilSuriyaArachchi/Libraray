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

import pronto.java.task.library.model.Book;
import pronto.java.task.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	public static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STAFF') OR hasAuthority('MEMBER')")
	@GetMapping({"/list"})
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STAFF') OR hasAuthority('MEMBER')")
	@GetMapping({"/filterbybook/{name}"})
	public List<Book> getBookByName(@PathVariable String name) {
		return bookService.getBookByName(name);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STAFF') OR hasAuthority('MEMBER')")
	@GetMapping({"/filterbyauthor/{name}"})
	public List<Book> getBookByAuthor(@PathVariable String name) {
		return bookService.getBookByAuthor(name);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STAFF')")
	@PostMapping("/save")
	public ResponseEntity<?> saveBook(@RequestBody Book book) {
		Book savedBook=null;
		try {
			savedBook=bookService.save(book);
			return ResponseEntity.ok(savedBook);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("ERROR", HttpStatus.BAD_REQUEST);
		}
	 
	}
}



package pronto.java.task.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import pronto.java.task.library.model.Author;
import pronto.java.task.library.repo.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository repository;

	public List<Author> getAllAuthors(){
		return repository.findAll();
		
	}
	
	public List<Author> getAuthorByName(String name){
		return repository.findAll();
		
	}

	public Author save(Author author) {
		return repository.save(author);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

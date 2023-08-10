package pronto.java.task.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pronto.java.task.library.enums.Role;
import pronto.java.task.library.model.User;
import pronto.java.task.library.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public List<User> getAllUsers(){
		return repository.findAll();
		
	}
	
	public User save(User user) {
		user.setPassWord(bcryptEncoder.encode(user.getPassWord()));
		user.setRole(user.getRole()!=null ? user.getRole() : Role.MEMBER.toString());
		return repository.save(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}

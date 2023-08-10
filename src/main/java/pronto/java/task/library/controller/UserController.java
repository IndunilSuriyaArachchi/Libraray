package pronto.java.task.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pronto.java.task.library.model.User;
import pronto.java.task.library.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('ADMIN')  OR hasAuthority('STAFF')")
	@GetMapping({"/list"})
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		
		User savedUser=null;
		try {
			savedUser=userService.save(user);
			return ResponseEntity.ok(savedUser);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("ERROR", HttpStatus.BAD_REQUEST);
		}
	}
}

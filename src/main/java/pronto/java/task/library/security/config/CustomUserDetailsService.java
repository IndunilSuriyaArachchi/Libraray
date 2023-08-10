package pronto.java.task.library.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pronto.java.task.library.security.dto.UserDTO;
import pronto.java.task.library.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles=null;
		
		pronto.java.task.library.model.User user = userRepository.findByUsername(username);
		if (user != null) {
			
			if(user.getRole().contains(",")) {
				String roleArr[]=user.getRole().split(",");
				roles=new ArrayList<SimpleGrantedAuthority>();
				for (String role : roleArr) {
					roles.add(new SimpleGrantedAuthority(role));
				}
			}else {
				roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			}
			return new User(user.getUserName(), user.getPassWord(), roles);
		}

		
		/*if(username.equals("admin"))
		{
		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new User("admin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe",
					roles);
		}
		else if(username.equals("user"))
		{
		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		return new User("user", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2",
					roles);
		}*/
		
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	
	
	public pronto.java.task.library.model.User save(UserDTO user) {
		pronto.java.task.library.model.User newUser = new pronto.java.task.library.model.User();
		newUser.setUserName(user.getUsername());
		newUser.setPassWord(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return userRepository.save(newUser);
	}

}

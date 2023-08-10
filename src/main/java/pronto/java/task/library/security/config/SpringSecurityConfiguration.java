package pronto.java.task.library.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
  	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		
		/*.antMatchers("/authenticate", "/register").permitAll().anyRequest().authenticated()
		.and().httpBasic();*/
		//if any exception occurs call this
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// make sure we use stateless session; session won't be used to
		// store user's state.
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		
		//.ignoring().antMatchers("/h2-console/**");
		//.authorizeRequests().antMatchers("/authors/save").hasAuthority("ADMIN")
		//.antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
		.authorizeRequests().antMatchers("/authenticate","/users/save", "/h2-console/**","/websocket","/api-docs/**","/swagger-ui/**").permitAll().anyRequest().authenticated()
		//.authorizeRequests().antMatchers("/**").permitAll()
		.and()
        .headers().frameOptions().disable()
		.and()
        .csrf().ignoringAntMatchers("/h2-console/**","/users/save","/websocket","/api-docs/**","/swagger-ui/**")
		.and()
		.csrf().disable()
		.cors().disable();
 
		//Add a filter to validate the tokens with every request
		http.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}

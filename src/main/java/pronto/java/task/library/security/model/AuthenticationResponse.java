package pronto.java.task.library.security.model;

public class AuthenticationResponse {

	private String token;
	
	public AuthenticationResponse() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [token=" + token + "]";
	}
	
	
	
}

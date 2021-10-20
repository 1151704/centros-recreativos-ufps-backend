package ufps.centrosrecreativos.api.auth;

import java.io.Serializable;

public class UserLoginModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public UserLoginModel() {
	}

	public UserLoginModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package fi.hh.PaOh.Harjoitustyo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

//Luodaan singupform luokan, jotta voidaan tallentaa väliaikaisesti tietoja , kun yritetään tehdä käyttäjää, jonka jälkeen onnistuneesta
//käyttäjän luomisesta signupformin tiedot tallennetaan user repositoryyn.
public class SignUpForm {
	
	@NotNull
	@NotEmpty
	@Size(min=5, max=15, message="Username size must be between 5 and 15")
	private String username = "";
	
	@NotNull
	@NotEmpty
	@Size(min=6, max=20, message="Password size must be between 6 and 20")
	private String password = "";
	
	@NotNull
	@NotEmpty
	@Size(min=6, max=20, message="Passwords must match")
	private String passwordCheck = "";
	
	
	private String userEmail = "";
	
	@NotNull
	@NotEmpty
	private String role = "USER";

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

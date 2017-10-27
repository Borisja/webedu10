/**
 * 
 */
package model;

import dao.CreateUserDOA;

/**
 * @author Boris Janjic
 *
 */
public class CreateUserModel {
	
	private String firstName;
	private String lastName;
	private String password;
	private String role;
	private String email;

	/**
	 * @param args
	 */
	
	public CreateUserModel(String firstName, String lastName, String email, String password, String role) {
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		setRole(role);
		setEmail(email);
	}
	
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	private void setRole(String rol) {
		this.role = rol;
	}
	
	public String getRol() {
		return this.role;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void createUser() { 
		CreateUserDOA doa = new CreateUserDOA(getFirstName(), getLastName(), getRol(), getEmail(), getPassword());
	}

}

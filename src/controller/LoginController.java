package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.ConnectDAO;
import model.EmployeeModel;

public class LoginController {
	/**
	 * Class to control the login process.
	 * @author nach7vs
	 *
	 */
	ConnectDAO connect_db = new ConnectDAO();
	
	public LoginController(){
		
	}
	
	/**
	 * Method to assign a user to a empty user model to be used throughout the application.
	 * @param email - users email to verify login
	 * @param pw - users password to verify login
	 * @return a user model if login was successful
	 */
	public EmployeeModel login_assignment(String email, String pw){

		String login_sql = "SELECT * "
				+ "FROM employee, employee_version "
				+ "WHERE employee_version_email = ? AND employee_version_password = ?";
		PreparedStatement login_statement;
		
		try {
			login_statement = connect_db.connectToDB().prepareStatement(login_sql);
			login_statement.setString(1, email);
			login_statement.setString(2, pw);
			ResultSet user_set = login_statement.executeQuery();
			
			while(user_set.next()){
				EmployeeModel user = new EmployeeModel(
						user_set.getInt("employee_id"),
						user_set.getBoolean("employee_isdeleted"),
						user_set.getString("employee_version_firstname"),
						user_set.getString("employee_version_lastname"),
						user_set.getString("employee_version_password"),
						user_set.getString("employee_version_email"),
						user_set.getString("employee_version_role"));
				return user;
			}
			login_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method to verify login.
	 * @param email - users email to verify login
	 * @param pw - users password to verify login
	 * @return a user model if login was successful
	 */
	public void login_request(String email, String pw){
		EmployeeModel user = this.login_assignment(email, pw);
		if(user == null){
			System.out.println("Login failed...E-mail and/or password do not match!");
		} else {
			System.out.println("Login success, assigning user id... " + user.getEmployeeId());
		}
	}
}

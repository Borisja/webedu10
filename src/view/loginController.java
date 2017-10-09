package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.ConnectDAO;
import model.GebruikerModel;

public class loginController {
	ConnectDAO connect_db = new ConnectDAO();
	
	public loginController(){
		
	}
	
	/**
	 * Method to assign a user to a empty user model to be used throughout the application.
	 * @param email - users email to verify login
	 * @param pw - users password to verify login
	 * @return a user model if login was successful
	 */
	public GebruikerModel login_assignment(String email, String pw){

		String login_sql = "SELECT * FROM gebruiker WHERE gebruiker_email = ? AND gebruiker_wachtwoord = ?";
		PreparedStatement login_statement;
		
		try {
			login_statement = connect_db.connectToDB().prepareStatement(login_sql);
			login_statement.setString(1, email);
			login_statement.setString(2, pw);
			ResultSet user_set = login_statement.executeQuery();
			
			while(user_set.next()){
				GebruikerModel user = new GebruikerModel(
						user_set.getInt("gebruiker_id"),
						user_set.getString("gebruiker_voornaam"),
						user_set.getString("gebruiker_achternaam"),
						user_set.getString("gebruiker_wachtwoord"),
						user_set.getString("gebruiker_email"),
						user_set.getInt("gebruiker_totaalUren"),
						user_set.getInt("gebruiker_sleutel"),
						user_set.getInt("gebruiker_rol")
						);
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
		GebruikerModel user = this.login_assignment(email, pw);
		if(user == null){
			System.out.println("Login failed...E-mail and/or password do not match!");
		} else {
			System.out.println("Login success, assigning user id... " + user.getGebruikerId());
		}
	}
}

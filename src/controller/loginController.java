package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.connectDao;
import model.gebruikerModel;

public class loginController {
	connectDao connect_db = new connectDao();
	
	public loginController(){
		
	}
	
	//Hoi Boris.
	
	public gebruikerModel login_assignment(String email, String pw){

		String login_sql = "SELECT * FROM gebruiker WHERE gebruiker_email = ? AND gebruiker_wachtwoord = ?";
		PreparedStatement login_statement;
		
		try {
			login_statement = connect_db.connectToDB().prepareStatement(login_sql);
			login_statement.setString(1, email);
			login_statement.setString(2, pw);
			ResultSet user_set = login_statement.executeQuery();
			
			while(user_set.next()){
				gebruikerModel user = new gebruikerModel(
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
}

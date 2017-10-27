package controller;


import java.io.IOException;
import java.text.ParseException;

import dao.AdministratorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.EmployeeModel;
import view.CalenderView;
import view.startScreen.AdministrationViewController;
import view.startScreen.UserViewController;

public class LoginController {
	/**
	 * Class to control the login process.
	 * @author nach7vs
	 *
	 */

	@FXML private TextField txtEmail;
	@FXML private TextField txtPassword;
	@FXML private Button loginButton;
	
	/**
	 * Method to verify login.
	 * Take the administrator dao class to assign a user if login is successful.
	 * @param email - users email to verify login
	 * @param password - users password to verify login
	 * @return a user model if login was successful
	 * @throws ParseException 
	 */
	public void loginRequest(String email, String password) throws ParseException{
		AdministratorDAO admin = new AdministratorDAO();
		EmployeeModel user = admin.loginAssignment(email, password);
		
		//If null login failed. This should be displayed to the user.
		if(user == null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Login Failed");
			alert.setHeaderText("E-mail or password was incorrect!");
			alert.setContentText("Contact your administrator for help.");

			alert.showAndWait();
		} else {
			//Here we open the screen after login, use user.getEmployeeRol() to get users rol and then load correct view. 

			if(user.getEmployeeRole().equals("employee")){
				UserViewController userViewController = new UserViewController();
				try {
					userViewController.startUser(user);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(user.getEmployeeRole().equals("manager"))
			{
				try {
					new AdministrationViewController().startAManager(user);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			} 
		}
	}
}

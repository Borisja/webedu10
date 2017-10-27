package view;

import java.text.ParseException;

import controller.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView {
	/**
	 * Launch view for the user to login.
	 * Works with LoginController to verify user login.
	 */
	LoginController login_controller = new LoginController();
	private Scene login_scene;
	private Label usernameLabel, passwordLabel;
	private TextField usernameDescription;
	private PasswordField passwordField;
	private GridPane loginGridPane;
	private Button loginButton;
	private Stage loginStage;
	
	public void show_login_view() {
		loginStage = new Stage();
		usernameLabel = new Label("Username: ");
		passwordLabel = new Label("Password : ");
		usernameDescription = new TextField();
		passwordField = new PasswordField();
		loginGridPane = new GridPane();
		loginButton = new Button("Login");
		
		loginGridPane.add(usernameLabel, 0, 0);
		loginGridPane.add(passwordLabel, 0, 1);
		loginGridPane.add(usernameDescription, 1, 0);
		loginGridPane.add(passwordField, 1, 1);
		loginGridPane.add(loginButton, 0, 2);
		
		loginButton.setOnAction(e-> {
			try {
				login_controller.loginRequest(usernameDescription.getText(), passwordField.getText());
				loginStage.close();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		login_scene = new Scene(loginGridPane, 300, 200);
		loginStage.setScene(login_scene);
		loginStage.setTitle("Login");
		
		loginStage.show();
	}
}

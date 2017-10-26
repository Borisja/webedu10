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
	private Label lbl_username, lbl_password;
	private TextField txt_username;
	private PasswordField pw_field;
	private GridPane login_gp;
	private Button btn_login;
	private Stage login_stage;
	
	public void show_login_view() {
		login_stage = new Stage();
		lbl_username = new Label("Username: ");
		lbl_password = new Label("Password : ");
		txt_username = new TextField();
		pw_field = new PasswordField();
		login_gp = new GridPane();
		btn_login = new Button("Login");
		
		login_gp.add(lbl_username, 0, 0);
		login_gp.add(lbl_password, 0, 1);
		login_gp.add(txt_username, 1, 0);
		login_gp.add(pw_field, 1, 1);
		login_gp.add(btn_login, 0, 2);
		
		btn_login.setOnAction(e-> {
			try {
				login_controller.loginRequest(txt_username.getText(), pw_field.getText());
				login_stage.close();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		login_scene = new Scene(login_gp, 300, 200);
		login_stage.setScene(login_scene);
		login_stage.setTitle("Login");
		
		login_stage.show();
	}
}

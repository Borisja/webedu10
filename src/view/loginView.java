package view;

import controller.loginController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class loginView {
	loginController login_controller = new loginController();
	public void show_login_view() {
		Stage login_stage = new Stage();
		Scene login_scene;
		Label lbl_username = new Label("Username: ");
		Label lbl_password = new Label("Password : ");
		TextField txt_username = new TextField();
		PasswordField pw_field = new PasswordField();
		GridPane login_gp = new GridPane();
		Button btn_login = new Button("Login");
		
		login_gp.add(lbl_username, 0, 0);
		login_gp.add(lbl_password, 0, 1);
		login_gp.add(txt_username, 1, 0);
		login_gp.add(pw_field, 1, 1);
		login_gp.add(btn_login, 0, 2);
		
		btn_login.setOnAction(e-> {
			login_controller.login_request(txt_username.getText(), pw_field.getText());
		});

		login_scene = new Scene(login_gp, 300, 200);
		login_stage.setScene(login_scene);
		login_stage.setTitle("Login");
		
		login_stage.show();
	}
}

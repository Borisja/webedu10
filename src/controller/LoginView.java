package controller;

import java.io.IOException;
import java.text.ParseException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.lang.Runnable;

public class LoginView {
	/**
	 * Launch view for the user to login.
	 * Works with LoginController to verify user login.
	 */
	LoginController login_controller = new LoginController();
	@FXML private TextField usernameDescription;
	@FXML private PasswordField passwordField;
	private Stage loginStage;
	@FXML Pane pane;
	
	@FXML private void initialize() {
		pane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent keyEvent) {
	            if (keyEvent.getCode() == KeyCode.ENTER) {
	                try {
	                	keyEvent.consume();
						login();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    });
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	usernameDescription.requestFocus();
            }
        });
	}
	
	public void login() throws ParseException
	{
		login_controller.loginRequest(usernameDescription.getText(), passwordField.getText(), loginStage);
		
	}
			
	
	public void show_login_view() throws IOException {
		Stage currentStage = new Stage();
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));			//get xml file
	    Pane loginView = loginLoader.load();
	    LoginView loginViewController = loginLoader.getController();
	    loginViewController.setCurrentStage(currentStage);
	    Scene scene = new Scene(loginView);
		currentStage.setScene(scene);
		currentStage.show();
	}


	private void setCurrentStage(Stage currentStage) {
		this.loginStage = currentStage;
		
	}
	

}

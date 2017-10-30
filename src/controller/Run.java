package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;

public class Run extends Application{

	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginView().show_login_view();
		
	}
}

package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.loginView;

public class Run extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new loginView().show_login_view();
	}
}

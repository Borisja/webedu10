package controller;

import dao.UserStoryDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application{

	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginView().show_login_view();
		//new UserStoryDAO().userStory_list().forEach(e->System.out.println(e.isDeleted()));
	}
}

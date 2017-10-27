package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import view.LoginView;

public class Run extends Application{

	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

<<<<<<< HEAD
//		 Parent root = FXMLLoader.load(getClass().getResource("/view/beginScherm/GebruikerView.fxml"));
//	       Scene scene = new Scene(root);
//	       primaryStage.setScene(scene);
//	       primaryStage.show();
//		GebruikerViewController view = new GebruikerViewController();
//		view.startGebruiker();

		Parent root = FXMLLoader.load(getClass().getResource("/view/AddEntryView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
=======
		new LoginView().show_login_view();
>>>>>>> c8756436279a975e89f7d71d1bae94aceb0a0b73

	}
}

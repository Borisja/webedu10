package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import view.LoginView;
import view.beginScherm.AdministratieViewController;
import dao.ProjectDAO;

public class Run extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("/view/beginScherm/GebruikerView.fxml"));
	       Scene scene = new Scene(root);
	       primaryStage.setScene(scene);
	       primaryStage.show();

//		Parent root = FXMLLoader.load(getClass().getResource("ProjectBeheer.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		System.out.println(this.getClass().toString()+": geinstancieerd");
//		primaryStage.show();
//		new ProjectBeheerController();
	}
}

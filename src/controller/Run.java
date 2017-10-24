package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import view.LoginView;
import dao.ProjectDAO;

public class Run extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		 Parent root = FXMLLoader.load(getClass().getResource("/view/beginScherm/GebruikerView.fxml"));
//	       Scene scene = new Scene(root);
//	       primaryStage.setScene(scene);
//	       primaryStage.show();
		// TODO Auto-generated method stub
		//new LoginView().show_login_view();

		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("ProjectBeheer.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		System.out.println(this.getClass().toString()+": geinstancieerd");
		primaryStage.show();
		ProjectBeheerController projectBeheerController=loader.getController();
//		ProjectBeheerController projectBeheerController = new ProjectBeheerController();
//		projectBeheerController.setLoader(loader);
	}
}

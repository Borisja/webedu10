package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import view.LoginView;
import view.beginScherm.AdministratieViewController;
import view.beginScherm.GebruikerViewController;
import dao.ProjectDAO;

public class Run extends Application{

	

    DeclareEntryController dce = new DeclareEntryController();
	
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
//		System.out.println(new ProjectDAO().project_list().size());
//
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddHours.fxml"));
//		Parent root = loader.load();
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		System.out.println(this.getClass().toString()+": geinstancieerd");
//		primaryStage.show();
	
		
//		ProjectBeheerController projectBeheerController=loader.getController();

//		GebruikerViewController view = new GebruikerViewController();
//		view.startGebruiker();

//		 Parent root = FXMLLoader.load(getClass().getResource("/view/AddEntryView.fxml"));
//	       Scene scene = new Scene(root);
//	       primaryStage.setScene(scene);
//	       primaryStage.show();

		// TODO Auto-generated method stub
		new LoginView().show_login_view();
	}
}

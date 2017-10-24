package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import view.LoginView;
<<<<<<< HEAD
import view.beginScherm.administratieViewController;
=======
import view.beginScherm.AdministratieViewController;
>>>>>>> branch 'master' of https://github.com/Borisja/webedu10.git
import dao.ProjectDAO;

public class Run extends Application{

	public static void main(String[] args) {
<<<<<<< HEAD
		launch(args);
		
=======
		Application.launch(args);
>>>>>>> branch 'master' of https://github.com/Borisja/webedu10.git
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		 Parent root = FXMLLoader.load(getClass().getResource("/view/beginScherm/GebruikerView.fxml"));
//	       Scene scene = new Scene(root);
//	       primaryStage.setScene(scene);
//	       primaryStage.show();
<<<<<<< HEAD
		// TODO Auto-generated method stub
		new LoginView().show_login_view();
	
=======
		System.out.println(new ProjectDAO().project_list().size());
>>>>>>> branch 'master' of https://github.com/Borisja/webedu10.git

//		Parent root = FXMLLoader.load(getClass().getResource("ProjectBeheer.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		System.out.println(this.getClass().toString()+": geinstancieerd");
//		primaryStage.show();
//		new ProjectBeheerController();
	}
}

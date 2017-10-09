package controller;


import javafx.application.Application;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Run extends Application {
=======
import javafx.stage.Stage;
import view.LoginView;
import dao.ProjectDAO;

public class Run extends Application{
>>>>>>> branch 'master' of https://github.com/Borisja/webedu10.git

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Application.launch(args);
//		loginController lc = new loginController();
//		lc.login_request("lmao@lmao.com", "password2");
//		ProjectDAO test = new ProjectDAO();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
<<<<<<< HEAD
		 Parent root = FXMLLoader.load(getClass().getResource("/view/GebruikerView.fxml"));
	       Scene scene = new Scene(root);
	       primaryStage.setScene(scene);
	       primaryStage.show();
		
	}


=======
		// TODO Auto-generated method stub
		new LoginView().show_login_view();
	}
>>>>>>> branch 'master' of https://github.com/Borisja/webedu10.git
}

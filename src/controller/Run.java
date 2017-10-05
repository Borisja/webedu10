package controller;

<<<<<<< HEAD
import javafx.application.Application;
import javafx.stage.Stage;
import view.loginView;
=======
import dao.ProjectDAO;
import model.GebruikerModel;
>>>>>>> eadb6b568835a65df15163c46f074c59cdedc141

public class Run extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		Application.launch(args);
=======
//		loginController lc = new loginController();
//		lc.login_request("lmao@lmao.com", "password2");
		ProjectDAO test = new ProjectDAO();
		
>>>>>>> eadb6b568835a65df15163c46f074c59cdedc141
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new loginView().show_login_view();
	}
}

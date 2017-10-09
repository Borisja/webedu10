package controller;


import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;
import dao.ProjectDAO;

public class Run extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
//		loginController lc = new loginController();
//		lc.login_request("lmao@lmao.com", "password2");
//		ProjectDAO test = new ProjectDAO();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new LoginView().show_login_view();
	}
}

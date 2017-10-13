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

		// TODO Auto-generated method stub
		Application.launch(args);
//		loginController lc = new loginController();
//		lc.login_request("lmao@lmao.com", "password2");
//		ProjectDAO test = new ProjectDAO();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		 Parent root = FXMLLoader.load(getClass().getResource("/view/GebruikerView.fxml"));
//	       Scene scene = new Scene(root);
//	       primaryStage.setScene(scene);
//	       primaryStage.show();
		// TODO Auto-generated method stub
//		new LoginView().show_login_view();
		
		Parent root = FXMLLoader.load(getClass().getResource("ProjectBeheer.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		System.out.println(this.getClass().toString()+": geinstancieerd");
		primaryStage.show();
		new ProjectBeheerController();
	}
}

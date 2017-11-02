package controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;
/**
 * Deze klasse is de controller van HomeView
 * @author rezanaser
 *
 */
public class homeController extends Application{
	@FXML Button homeButton;
	private Stage primaryStage;
	private EmployeeModel user;
	
	public void showManual() throws IOException{
		Stage manualStage = new Stage();
		FXMLLoader approveManagementLoader = new FXMLLoader(getClass().getResource("/view/Manual.fxml"));			//get xml file
	    Pane approvalview = approveManagementLoader.load();
        Scene scene = new Scene(approvalview);
        manualStage.setScene(scene);
        manualStage.show();
        
	}
	
	public void setUser(EmployeeModel employeeModel){
		this.user=employeeModel;
	}
	
	/**
	 * Deze methode wordt aangeropen door de gebruikerViewController en geeft een string van gebruikersnaam mee 
	 * 
	 * @param name: Dit is naam dat meekegregen is van de hoofd controller
	 * @author rezanaser
	 */
	public void setUserName(String name)
	{
		this.userName.setText("Welkom "+name);
	}
	
	/**
	 * Deze methode wordt aangeropen door de gebruikerViewController en geeft de pane mee
	 * 
	 * @param name: Dit is de mainPane dat  meegekregen is van  de hoofd controller
	 * @author rezanaser
	 */
	public void setUserStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
	
	@FXML Label userName;

	/**
	 * Deze methode wordt aangeropen wanneer er gklikt wordt op de HOME button en gaat terug naar de mainPane
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void backToHome() throws IOException
	{
		System.out.println(this.getClass().toString()+": uitloggen werkt");
		Alert areYouSure = new Alert(AlertType.CONFIRMATION);
		areYouSure.setContentText("Weet u zeker dat u wilt uitloggen? ");
		Optional<ButtonType> result = areYouSure.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			this.primaryStage.close();
			new LoginView().show_login_view();
		}
	
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

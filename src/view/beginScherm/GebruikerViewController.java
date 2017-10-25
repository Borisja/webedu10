package view.beginScherm;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;
import view.home.homeController;
import view.projectView.ProjectController;
import view.sprintView.SprintController;

public class GebruikerViewController {
	@FXML Button btn;
	@FXML Button btn1;
	@FXML Pane pane;
	private SprintController scC;
	private ProjectController pjC;
	
	public void startGebruiker(EmployeeModel em) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader gebruikerView = new FXMLLoader(getClass().getResource("/view/beginScherm/GebruikerView.fxml"));
		BorderPane view =(BorderPane) gebruikerView.load();
		GebruikerViewController gebruikerController = gebruikerView.getController();
		
		
		FXMLLoader sprintLoader = new FXMLLoader(getClass().getResource("/view/sprintView/SprintOverzichtView.fxml"));
		Pane sprintView = sprintLoader.load();
		SprintController sprintController = sprintLoader.getController();
		
		FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/projectView/ProjectView.fxml"));
		Pane projectView = projectLoader.load();
		ProjectController projectController = projectLoader.getController();
		
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    view.setTop(homeView);
	    homeController.setUserName(em.getEmployeeVoornaam());  
	    
		gebruikerController.setControllerSprint(sprintController);
		gebruikerController.setControllerProject(projectController);
		
		Pane thePane = (Pane)gebruikerView.getNamespace().get("pane");
		thePane.getChildren().addAll(sprintView,projectView);
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public void setControllerSprint (SprintController sc){
		this.scC = sc;
	}
	
	public void setControllerProject (ProjectController pc){
		this.pjC = pc;
	}
	
	public void toonSprintscherm()
	{
		this.scC.openSprintmenu();
	}

	public void toonProjectScherm()
	{
		this.pjC.openProjectScherm();
		System.out.println("hola");
	}
		
}

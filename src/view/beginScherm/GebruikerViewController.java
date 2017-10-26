package view.beginScherm;
/**
 * This class loads fxml files of the employeesceen and initializes their respective controllers.
 */

import java.io.IOException;

import controller.AddHoursController;
import controller.GebruikerGegevensController;
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
import view.AddEntryViewController;
import view.home.homeController;
import view.projectView.ProjectController;
import view.sprintView.SprintController;

public class GebruikerViewController {
	@FXML Button btn;
	@FXML Button btn1;
	@FXML Pane pane;
	private SprintController scC;
	private ProjectController pjC;
	private AddEntryViewController entryController;
	private AddHoursController addHours;
	private GebruikerGegevensController gebruikerGegevensController;
	private EmployeeModel user;
	
	public void startGebruiker(EmployeeModel em) throws IOException
	{
		this.user = em;
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
		
		FXMLLoader addHoursLoader = new FXMLLoader(getClass().getResource("/controller/AddHours.fxml"));
		Pane addHoursView = addHoursLoader.load();
		AddHoursController addHoursController = addHoursLoader.getController();
		
		FXMLLoader addEntryLoader = new FXMLLoader(getClass().getResource("/view/AddEntryView.fxml"));
		Pane addEntryView = addEntryLoader.load();
		AddEntryViewController addEntryController = addEntryLoader.getController();
		
		
		FXMLLoader employeeGegevensLoader = new FXMLLoader(getClass().getResource("/controller/GebruikerGegevensView.fxml"));
		Pane gebruikerGegevensView = employeeGegevensLoader.load();
		GebruikerGegevensController gebruikerGegevensController = employeeGegevensLoader.getController();
		
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    
	    homeController.setGebruikerPane((Pane)gebruikerView.getNamespace().get("pane"));
	    
	    
	    view.setTop(homeView);
	    homeController.setUserName(em.getEmployeeVoornaam());  
	    gebruikerGegevensController.fillUserData(em);
		gebruikerController.setControllerSprint(sprintController);
		gebruikerController.setControllerProject(projectController);
		gebruikerController.setControllerAddEntryHours(addEntryController);
		addEntryController.setController(addHoursController);
		gebruikerController.setControllerGebruikerGegevens(gebruikerGegevensController);
		
		
		Pane thePane = (Pane)gebruikerView.getNamespace().get("pane");
		thePane.getChildren().addAll(sprintView,projectView,addHoursView, addEntryView,gebruikerGegevensView);
		
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	private void setControllerGebruikerGegevens(GebruikerGegevensController gebruikerGegevensController) {
		this.gebruikerGegevensController = gebruikerGegevensController;
		
	}


	private void setControllerAddEntryHours(AddEntryViewController addEntryController) {
		this.entryController = addEntryController;
		
	}
	public void openGebruikerGegevensView()
	{
		this.gebruikerGegevensController.showView();
	}

	public void openAddEntryView()
	{
		this.entryController.showView();
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

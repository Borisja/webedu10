package view.startScreen;
/**
 * This class loads fxml files of the employeesceen and initializes their respective controllers.
 */

import java.io.IOException;

import controller.EntryController;
import controller.UserInformationController;
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

public class UserViewController {
	@FXML Pane pane;
	private SprintController sprintController;
	private ProjectController projectController;
	private AddEntryViewController entryController;
	private EntryController addHours;
	private UserInformationController userInformationController;
	private EmployeeModel user;
	
	public void startUser(EmployeeModel employee) throws IOException
	{
		this.user = employee;
		Stage primaryStage = new Stage();
		FXMLLoader userView = new FXMLLoader(getClass().getResource("/view/startScreen/UserView.fxml"));
		BorderPane view = (BorderPane) userView.load();
		UserViewController gebruikerController = userView.getController();
		
		
		FXMLLoader sprintLoader = new FXMLLoader(getClass().getResource("/view/sprintView/SprintOverviewView.fxml"));
		Pane sprintView = sprintLoader.load();
		SprintController sprintController = sprintLoader.getController();
		
		FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/projectView/ProjectView.fxml"));
		Pane projectView = projectLoader.load();
		ProjectController projectController = projectLoader.getController();
		
		FXMLLoader addHoursLoader = new FXMLLoader(getClass().getResource("/view/AddHours.fxml"));
		Pane addHoursView = addHoursLoader.load();
		EntryController addHoursController = addHoursLoader.getController();
		
		FXMLLoader addEntryLoader = new FXMLLoader(getClass().getResource("/view/AddEntryView.fxml"));
		Pane addEntryView = addEntryLoader.load();
		AddEntryViewController addEntryController = addEntryLoader.getController();
		
		
		FXMLLoader employeeGegevensLoader = new FXMLLoader(getClass().getResource("/controller/UserInformationView.fxml"));
		Pane gebruikerGegevensView = employeeGegevensLoader.load();
		UserInformationController gebruikerGegevensController = employeeGegevensLoader.getController();
		
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    
	    homeController.setUserPane((Pane)userView.getNamespace().get("pane"));
	    
	    
	    view.setTop(homeView);
	    homeController.setUserName(employee.getEmployeeFirstname());  
	    gebruikerGegevensController.fillUserData(employee);
		gebruikerController.setControllerSprint(sprintController);
		gebruikerController.setControllerProject(projectController);
		gebruikerController.setControllerAddEntryHours(addEntryController);
		addEntryController.setController(addHoursController);
		gebruikerController.setControllerGebruikerGegevens(gebruikerGegevensController);
		
		
		Pane thePane = (Pane)userView.getNamespace().get("pane");
		thePane.getChildren().addAll(sprintView,projectView,addHoursView, addEntryView,gebruikerGegevensView);
		
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	private void setControllerGebruikerGegevens(UserInformationController gebruikerGegevensController) {
		this.userInformationController = gebruikerGegevensController;
		
	}


	private void setControllerAddEntryHours(AddEntryViewController addEntryController) {
		this.entryController = addEntryController;
		
	}
	public void openUserInformationView()
	{
		this.userInformationController.showView();
	}

	public void openAddEntryView()
	{
		this.entryController.showView();
	}

	public void setControllerSprint (SprintController sc){
		this.sprintController = sc;
	}
	
	public void setControllerProject (ProjectController pc){
		this.projectController = pc;
	}
	
	public void showSprintScreen()
	{
		this.sprintController.openSprintmenu();
	}

	public void showProjectScreen()
	{
		this.projectController.openProjectScherm();
		System.out.println("hola");
	}
		
}

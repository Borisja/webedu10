package view.startScreen;

import java.io.IOException;

import view.EntryController;
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

/**
 * This class loads fxml files of the employeesceen and initializes their respective controllers.
 */

public class UserViewController {
	@FXML Button btn;
	@FXML Button btn1;
	@FXML Pane pane;
	private SprintController scC;
	private ProjectController pjC;
	private AddEntryViewController entryController;
	private EntryController addHours;
	private UserInformationController userInformationController;
	private EmployeeModel user;
	
	public void startGebruiker(EmployeeModel em) throws IOException
	{
		this.user = em;
		Stage primaryStage = new Stage();
		FXMLLoader userView = new FXMLLoader(getClass().getResource("/view/startScreen/UserView.fxml"));
		BorderPane view =(BorderPane) userView.load();
		UserViewController userController = userView.getController();
		
		
		FXMLLoader sprintLoader = new FXMLLoader(getClass().getResource("/view/sprintView/SprintOverviewView.fxml"));
		Pane sprintView = sprintLoader.load();
		SprintController sprintController = sprintLoader.getController();
		
		FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/projectView/ProjectView.fxml"));
		Pane projectView = projectLoader.load();
		ProjectController projectController = projectLoader.getController();
		
		FXMLLoader addHoursLoader = new FXMLLoader(getClass().getResource("/controller/AddHours.fxml"));
		Pane addHoursView = addHoursLoader.load();
		EntryController addHoursController = addHoursLoader.getController();
		
		FXMLLoader addEntryLoader = new FXMLLoader(getClass().getResource("/view/AddEntryView.fxml"));
		Pane addEntryView = addEntryLoader.load();
		AddEntryViewController addEntryController = addEntryLoader.getController();
		
		
		FXMLLoader employeeGegevensLoader = new FXMLLoader(getClass().getResource("/controller/UserInformationView.fxml"));
		Pane userInformationView = employeeGegevensLoader.load();
		UserInformationController userInformationController = employeeGegevensLoader.getController();
		
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    
	    homeController.setUserPane((Pane)userView.getNamespace().get("pane"));
	    
	    
	    view.setTop(homeView);
	    homeController.setUserName(em.getEmployeeFirstname());  
	    userInformationController.fillUserData(em);
	    userController.setControllerSprint(sprintController);
	    userController.setControllerProject(projectController);
	    userController.setControllerAddEntryHours(addEntryController);
		addEntryController.setController(addHoursController);
		userController.setControllerGebruikerGegevens(userInformationController);
		
		
		Pane thePane = (Pane)userView.getNamespace().get("pane");
		thePane.getChildren().addAll(sprintView,projectView,addHoursView, addEntryView,userInformationView);
		
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	private void setControllerGebruikerGegevens(UserInformationController userInformationController) {
		this.userInformationController = userInformationController;
		
	}


	private void setControllerAddEntryHours(AddEntryViewController addEntryController) {
		this.entryController = addEntryController;
		
	}
	public void openGebruikerGegevensView()
	{
		this.userInformationController.showView();
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

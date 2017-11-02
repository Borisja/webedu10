package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;



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
	private UserInformationController userInformationController;
	private EmployeeModel user;
	private CalenderView calenderView;
	
	public void startGebruiker(EmployeeModel em) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader userView = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
		BorderPane view =(BorderPane) userView.load();
		UserViewController userController = userView.getController();
		
		
		FXMLLoader sprintLoader = new FXMLLoader(getClass().getResource("/view/SprintOverviewView.fxml"));
		Pane sprintView = sprintLoader.load();
		SprintController sprintController = sprintLoader.getController();
		
		FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/ProjectView.fxml"));
		Pane projectView = projectLoader.load();
		ProjectController projectController = projectLoader.getController();
		
		FXMLLoader addEntryLoader = new FXMLLoader(getClass().getResource("/view/AddEntryView.fxml"));
		Pane addEntryView = addEntryLoader.load();
		AddEntryViewController addEntryController = addEntryLoader.getController();
		
		FXMLLoader entriesLoader = new FXMLLoader(getClass().getResource("/view/CalenderView.fxml"));
		Pane entriesView = entriesLoader.load();
		CalenderView calenderController = entriesLoader.getController();
		
		FXMLLoader employeeGegevensLoader = new FXMLLoader(getClass().getResource("/view/UserInformationView.fxml"));
		Pane userInformationView = employeeGegevensLoader.load();
		UserInformationController userInformationController = employeeGegevensLoader.getController();
		
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    
	    homeController.setUserStage(primaryStage);
	    
	    
	    view.setTop(homeView);
	    homeController.setUserName(em.getEmployeeFirstname());
	    userInformationController.fillUserData(em);
	    userController.setControllerSprint(sprintController);
	    userController.setControllerProject(projectController);
	    userController.setControllerAddEntryHours(addEntryController);
//	    calenderController.fillCalender(em); Is niet nodig. showCalender() heeft dit overgenomen
// 											doordat showCalenderView in CalenderView fillCalender aanroept
		calenderController.setEmployeeYou(em);
	    addEntryController.setCurrentUser(em);
	    sprintController.setCurrentUser(em);
	    sprintController.fillTabel(em.getEmployeeId());
	    userController.setControllerCalender(calenderController);
	    projectController.fillProjectsTable(em.getEmployeeId());
		userController.setControllerGebruikerGegevens(userInformationController);
		
		
		Pane thePane = (Pane)userView.getNamespace().get("pane");
		thePane.getChildren().addAll(sprintView,projectView,userInformationView,entriesView,addEntryView);
		
		
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	private void setControllerCalender(CalenderView calenderController) {
		this.calenderView = calenderController;
		
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
	public void showCalender()
	{
		this.calenderView.showCalenderView();
	}
		
}

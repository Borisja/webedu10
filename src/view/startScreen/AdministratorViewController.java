package view.startScreen;

import java.io.IOException;

import controller.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;
import view.approvalView.ApprovalController;
import view.home.homeController;
import view.manual.ManualController;
/**
 * Deze klasse is de controller van de beheerderview
 * @author rezanaser
 *
 */
public class AdministratorViewController {
	
	private @FXML Button btnAccounts;
	
	private @FXML Pane pane;

	private EmployeeManagementController employeeManagement;
	private ProjectManagementViewController projectManagementController;
	private CustomerManagementViewController customerManagement;
	private LockUserController lockEmployee;
	private SprintManagementViewController sprintManagementViewController;
	

	
	/**
	 * 
	 * Deze methode start het beheerder scherm.
	 * @throws IOException
	 * @author rezanaser
	 * @param user 
	 */
	public void startAdministrator(EmployeeModel user) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader beheerderScherm = new FXMLLoader(getClass().getResource("/view/startScreen/AdministratorView.fxml"));	
		BorderPane View  = (BorderPane)beheerderScherm.load();
		AdministratorViewController adminController = beheerderScherm.getController();

	    
	    FXMLLoader medewerkerBeheren = new FXMLLoader(getClass().getResource("/controller/EmployeeManagement.fxml"));			//get xml file
	    Pane medewerkerBeherenView = medewerkerBeheren.load();	
	    EmployeeManagementController medewerkerBeherenController = medewerkerBeheren.getController();
	    
	    FXMLLoader addEmployeeLoader = new FXMLLoader(getClass().getResource("/controller/CreateUser.fxml"));			//get xml file
	    Pane createUserView = addEmployeeLoader.load();	
	    CreateUserController createUserController = addEmployeeLoader.getController();
	    
	    FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/controller/CustomersManagement.fxml"));			//get xml file
	    Pane customerView = customerLoader.load();	
	    CustomerManagementViewController customerController = customerLoader.getController();
	    
	    FXMLLoader projectManagementLoader = new FXMLLoader(getClass().getResource("/view/ProjectsManagementView.fxml"));			//get xml file
	    Pane projectManagementView = projectManagementLoader.load();	
	    ProjectManagementViewController projectManagementController = projectManagementLoader.getController();
	    
	    FXMLLoader lockUnclockEmployeesLoader = new FXMLLoader(getClass().getResource("/view/LockUser.fxml"));			//get xml file
	    Pane unlockView = lockUnclockEmployeesLoader.load();	
	    LockUserController lockController = lockUnclockEmployeesLoader.getController();
	    
	    FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("/view/EmployeesOverview.fxml"));			//get xml file
	    Pane allEmployeeView = employeeLoader.load();	
	    EmployeesOverviewController allEmployeeController = employeeLoader.getController();
	    
	    FXMLLoader sprintManagementLoader = new FXMLLoader(getClass().getResource("/controller/SprintManagementView.fxml"));			//get xml file
	    Pane sprintManagementView = sprintManagementLoader.load();	
	    SprintManagementViewController sprintManagementViewController = sprintManagementLoader.getController();

//		FXMLLoader editEmployeeLoader = new FXMLLoader(getClass().getResource("/view/EditEmployee.fxml"));			//get xml file
//		Pane editEmployeeView = editEmployeeLoader.load();
//		EditEmployeeController editEmployeeController = editEmployeeLoader.getController();

		EditEmployeeController editEmployeeController = new EditEmployeeController();
		Pane editEmployeeView = editEmployeeController.getEditPane();

	    

		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
		Pane homeView = homeLoader.load();
		homeController homeController = homeLoader.getController();
		homeController.setUserName(user.getEmployeeFirstname());
		View.setTop(homeView);
	    adminController.setControllerMedewerkerBeheren(medewerkerBeherenController);
	    adminController.setControllerProjectManagement(projectManagementController);
	    adminController.setControllerCustomerManagement(customerController);
	    adminController.setControllerLockEmployee(lockController);
	    adminController.setControllerSprintManagement(sprintManagementViewController);
	    medewerkerBeherenController.setControllerEmployee(allEmployeeController);
	    medewerkerBeherenController.setControllerCreateUserController(createUserController);
	    medewerkerBeherenController.setControllerLockUserController(lockController);
	    medewerkerBeherenController.setControllerEditEmployeeController(editEmployeeController);
	   // beheerderController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)beheerderScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(editEmployeeView,allEmployeeView, unlockView, medewerkerBeherenView, createUserView, projectManagementView,customerView, sprintManagementView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setControllerSprintManagement(SprintManagementViewController sprintManagementViewController) {
		this.sprintManagementViewController = sprintManagementViewController;
	}

	private void setControllerLockEmployee(LockUserController lockController) {
		this.lockEmployee = lockController;
		
	}

	private void setControllerCustomerManagement(CustomerManagementViewController customerController) {
		this.customerManagement = customerController;
		
	}

	private void setControllerProjectManagement(ProjectManagementViewController projectManagementController) {
		this.projectManagementController = projectManagementController;
		
	}
	private void setControllerMedewerkerBeheren(EmployeeManagementController medewerkerBeherenController) {
		this.employeeManagement = medewerkerBeherenController;
		
	}
	public void openProjectsManagement()
	{
		this.projectManagementController.showView();
	}
	public void openMedewerkerView()
	{
		this.employeeManagement.showView();
	}
	public void openCustomerView()
	{
		this.customerManagement.showView();
	}
	
	public void openSprintManagementView()
	{
		this.sprintManagementViewController.showView();
	}

}

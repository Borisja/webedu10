package view.startScreen;

import java.io.IOException;

import controller.AccountManagementController;
import controller.CreateUserController;
import controller.CustomerManagementViewController;
import controller.EmployeeManagementController;
import controller.EmployeesOverviewController;
import controller.LockUserController;
import controller.ProjectManagementViewController;
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
	

	
	/**
	 * 
	 * Deze methode start het beheerder scherm. 
	 * @param beheerder_id Hier krijg hij de employee id mee
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

		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
		Pane homeView = homeLoader.load();
		homeController homeController = homeLoader.getController();
		homeController.setUserName(user.getEmployeeFirstname());
		View.setTop(homeView);
	    adminController.setControllerMedewerkerBeheren(medewerkerBeherenController);
	    adminController.setControllerProjectManagement(projectManagementController);
	    adminController.setControllerCustomerManagement(customerController);
	    adminController.setControllerLockEmployee(lockController);
	    medewerkerBeherenController.setControllerEmployee(allEmployeeController);
	    medewerkerBeherenController.setControllerCreateUserController(createUserController);
	    medewerkerBeherenController.setControllerLockUserController(lockController);
	   // beheerderController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)beheerderScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(allEmployeeView, unlockView, medewerkerBeherenView, createUserView, projectManagementView,customerView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
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
	


}

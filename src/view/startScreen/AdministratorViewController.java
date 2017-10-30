package view.startScreen;

import java.io.IOException;

import controller.AccountManagementController;
import controller.CreateUserController;
import controller.CustomerManagementViewController;
import controller.EmployeeManagementController;
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
	private AccountManagementController accountManagement;
	private CustomerManagementViewController customerManagement;
	

	
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

	    
	    FXMLLoader accountbeheren = new FXMLLoader(getClass().getResource("/view/AccountManagement.fxml"));			//get xml file
	    Pane accountBeherenView = accountbeheren.load();	
	    AccountManagementController accountController = accountbeheren.getController();
	    
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

		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
		Pane homeView = homeLoader.load();
		homeController homeController = homeLoader.getController();
		homeController.setUserName(user.getEmployeeFirstname());
		View.setTop(homeView);
		
	    adminController.setControllerAccount(accountController);
	    adminController.setControllerMedewerkerBeheren(medewerkerBeherenController);
	    adminController.setControllerProjectManagement(projectManagementController);
	    adminController.setControllerCustomerManagement(customerController);
	    medewerkerBeherenController.setControllerCreateUserController(createUserController);
	   // beheerderController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)beheerderScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(accountBeherenView, medewerkerBeherenView, createUserView, projectManagementView,customerView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setControllerCustomerManagement(CustomerManagementViewController customerController) {
		this.customerManagement = customerController;
		
	}

	private void setControllerProjectManagement(ProjectManagementViewController projectManagementController) {
		this.projectManagementController = projectManagementController;
		
	}

	private void setControllerAccount(AccountManagementController accountController) {
		this.accountManagement = accountController;
		
	}
	private void setControllerMedewerkerBeheren(EmployeeManagementController medewerkerBeherenController) {
		this.employeeManagement = medewerkerBeherenController;
		
	}
	public void openAccountView()
	{
		this.accountManagement.showAccountView();
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

package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;

/**
 * Deze klasse is bestemd voor de administratie. 
 * 
 * @author rezanaser
 * 
 * @throws IOException
 */

public class ManagerViewController {
	
	@FXML private Button approvalButton;
	@FXML private Pane pane;
	private ApprovalController approvalController;
	private ExportController export = new ExportController();
	private Label name;
	private CustomerManagementViewController customerController;
	private EmployeesOverviewController employeeController;

	/**
	 * Deze methode geeft de administratieView  na succesvol inloggen.
	 * @author rezanaser
	 * @throws IOException
	 */
	public void startManager(EmployeeModel em) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader administrationScreen = new FXMLLoader(getClass().getResource("/view/ManagerView.fxml"));	
		BorderPane View  = (BorderPane)administrationScreen.load();
		ManagerViewController administrationController = administrationScreen.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    homeController.setUserName(em.getEmployeeFirstname());
	    homeController.setUserStage(primaryStage);
	    
	    FXMLLoader approvalLoader = new FXMLLoader(getClass().getResource("/view/approvalView.fxml"));			//get xml file
	    Pane approvalView = approvalLoader.load();	
	    ApprovalController approvalController = approvalLoader.getController();
	    
	    FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("/view/EmployeesOverview.fxml"));			//get xml file
	    Pane allEmployeeView = employeeLoader.load();	
	    EmployeesOverviewController allEmployeeController = employeeLoader.getController();
	    
	    FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/CustomersManagement.fxml"));			//get xml file
	    Pane customerView = customerLoader.load();	
	    CustomerManagementViewController customerController = customerLoader.getController();

	    administrationController.setApprovalController(approvalController);
	    administrationController.setEmployeeOverviewController(allEmployeeController);
	    administrationController.setCustomerManagementController(customerController);
	    customerController.disableButtons();
	    approvalController.disableLockUnlock();
	    
	    Pane tabPane = (Pane)administrationScreen.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(allEmployeeView, approvalView, customerView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void setCustomerManagementController(CustomerManagementViewController customerController) {
		this.customerController = customerController;
		
	}
	private void setEmployeeOverviewController(EmployeesOverviewController allEmployeeController) {
		this.employeeController = allEmployeeController;
		
	}
	/**
	 * Deze methode krijgt de controller van andere views.
	 * @param approvalController
	 */
	public void setApprovalController(ApprovalController approvalController)
	{
		this.approvalController = approvalController;
	}

	public void toonGoedkeurenMenu()
	{
		this.approvalController.openApprovalMenu();
	}
	public void downloadCSV()
	{
		this.export.exportCSV();
	}
	public void openEmployeeView()
	{
		this.employeeController.showEmployeeOverview();
	}
	public void openCustomerView()
	{
		this.customerController.showView();
	}
	public void projectManagement(){
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
	}
}


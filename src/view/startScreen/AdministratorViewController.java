package view.startScreen;

import java.io.IOException;

import controller.AccountManagementController;
import controller.CreateUserController;
import controller.EmployeeManagementController;
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
	private AccountManagementController accountController;

	private EmployeeManagementController medewerkerBeheren;
	

	
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
		AdministratorViewController beheerderController = beheerderScherm.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    
	    FXMLLoader accountbeheren = new FXMLLoader(getClass().getResource("/controller/AccountManagement.fxml"));			//get xml file
	    Pane accountBeherenView = accountbeheren.load();	
	    AccountManagementController accountController = accountbeheren.getController();
	    
	    FXMLLoader medewerkerBeheren = new FXMLLoader(getClass().getResource("/controller/EmployeeManagement.fxml"));			//get xml file
	    Pane medewerkerBeherenView = medewerkerBeheren.load();	
	    EmployeeManagementController medewerkerBeherenController = medewerkerBeheren.getController();
	    
	    FXMLLoader addEmployeeLoader = new FXMLLoader(getClass().getResource("/controller/CreateUser.fxml"));			//get xml file
	    Pane createUserView = addEmployeeLoader.load();	
	    CreateUserController createUserController = addEmployeeLoader.getController();
	    
//	    FXMLLoader handleidingLoader = new FXMLLoader(getClass().getResource("/view/handleiding/handleiding.fxml"));			//get xml file
//	    Pane handleidingView = handleidingLoader.load();	
//	    handleidingController handleidingController = handleidingLoader.getController();

	    beheerderController.setControllerAccount(accountController);
	    beheerderController.setControllerMedewerkerBeheren(medewerkerBeherenController);
	    medewerkerBeherenController.setControllerCreateUserController(createUserController);
	   // beheerderController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)beheerderScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(accountBeherenView, homeView, medewerkerBeherenView, createUserView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setControllerAccount(AccountManagementController accountController) {
		this.accountController = accountController;
		
	}
	private void setControllerMedewerkerBeheren(EmployeeManagementController medewerkerBeherenController) {
		this.medewerkerBeheren = medewerkerBeherenController;
		
	}

	public void openMedewerkerView()
	{
		this.medewerkerBeheren.showView();
	}


	private void openAccountView()
	{
		this.accountController.showAccountView();
	}

}

package view.beginScherm;

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
import view.ExportController;
import view.approvalView.ApprovalController;
import view.home.homeController;
import view.manual.ManualController;

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
	private ManualController manualController;
	private ExportController export = new ExportController();
	private Label name;

	/**
	 * Deze methode geeft de administratieView  na succesvol inloggen.
	 * @author rezanaser
	 * @throws IOException
	 */
	public void startAdministrationManager(EmployeeModel em) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader administrationScreen = new FXMLLoader(getClass().getResource("/view/beginScherm/administratieView.fxml"));	
		BorderPane View  = (BorderPane)administrationScreen.load();
		ManagerViewController managerViewController = administrationScreen.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    homeController.setUserName(em.getEmployeeFirstname());
	    
	    
	    FXMLLoader approvalLoader = new FXMLLoader(getClass().getResource("/view/goedkeurenView/goedkeurenView.fxml"));			//get xml file
	    Pane approvalView = approvalLoader.load();	
	    ApprovalController approvalController = approvalLoader.getController();
	    
	    FXMLLoader manualLoader = new FXMLLoader(getClass().getResource("/view/handleiding/handleiding.fxml"));			//get xml file
	    Pane manualView = manualLoader.load();	
	    ManualController manualController = manualLoader.getController();

	    managerViewController.setApprovalController(approvalController);
	    managerViewController.setManualController(manualController);
	    
	    Pane tabPane = (Pane)administrationScreen.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(approvalView,manualView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * Deze methode krijgt de controller van andere views.
	 * @param approvalController
	 */
	public void setApprovalController(ApprovalController approvalController)
	{
		this.approvalController = approvalController;
	}
	public void setManualController(ManualController manualController)
	{
		this.manualController = manualController;
	}
	public void showManual()
	{
		this.manualController.openHandleidingMenu();
	}
	public void toonGoedkeurenMenu()
	{
		this.approvalController.openApprovalMenu();
	}
	public void downloadCSV()
	{
		this.export.exportCSV();
	}
}

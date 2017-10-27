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

public class AdministratieViewController {
	
	@FXML private Button approvalButton;
	@FXML private Pane pane;
	private ApprovalController approvalController;
	private ManualController manualController;
	private ExportController exportController = new ExportController();
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
		AdministratieViewController administratieController = administrationScreen.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    homeController.setUserName(em.getEmployeeFirstname());
	    
	    
	    FXMLLoader approvalLoader = new FXMLLoader(getClass().getResource("/view/goedkeurenView/goedkeurenView.fxml"));			//get xml file
	    Pane approvalView = approvalLoader.load();	
	    ApprovalController approvalController = approvalLoader.getController();
	    
	    FXMLLoader handleidingLoader = new FXMLLoader(getClass().getResource("/view/handleiding/handleiding.fxml"));			//get xml file
	    Pane handleidingView = handleidingLoader.load();	
	    ManualController manualController = handleidingLoader.getController();

	    administratieController.setControllerGoedkeuren(approvalController);
	    administratieController.setControllerHandleiding(manualController);
	    
	    Pane tabPane = (Pane)administrationScreen.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(approvalView,handleidingView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * Deze methode krijgt de controller van andere views.
	 * @param gk
	 */
	public void setControllerGoedkeuren(ApprovalController gk)
	{
		this.approvalController = gk;
	}
	public void setControllerHandleiding(ManualController gk)
	{
		this.manualController = gk;
	}
	public void toonHanleiding()
	{
		this.manualController.openHandleidingMenu();
	}
	public void toonGoedkeurenMenu()
	{
		this.approvalController.openApprovalMenu();
	}
	public void downloadCSV()
	{
		this.exportController.exportCSV();
	}
}

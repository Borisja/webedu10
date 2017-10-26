package view.startScreen;

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

public class AdministrationViewController {
	
	@FXML private Button btnGoedkeuren;
	@FXML private Pane pane;
	private ApprovalController gkC;
	private ManualController hdC;
	private ExportController export = new ExportController();
	private Label name;

	/**
	 * Deze methode geeft de administratieView  na succesvol inloggen.
	 * @author rezanaser
	 * @throws IOException
	 */
	public void startAManager(EmployeeModel em) throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader administratieScherm = new FXMLLoader(getClass().getResource("/view/beginScherm/administratieView.fxml"));	
		BorderPane View  = (BorderPane)administratieScherm.load();
		AdministrationViewController administratieController = administratieScherm.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    homeController.setUserName(em.getEmployeeVoornaam());
	    
	    
	    FXMLLoader goedkeurenLoader = new FXMLLoader(getClass().getResource("/view/goedkeurenView/goedkeurenView.fxml"));			//get xml file
	    Pane goedkeurenView = goedkeurenLoader.load();	
	    ApprovalController goedkeurenController = goedkeurenLoader.getController();
	    
	    FXMLLoader handleidingLoader = new FXMLLoader(getClass().getResource("/view/handleiding/handleiding.fxml"));			//get xml file
	    Pane handleidingView = handleidingLoader.load();	
	    ManualController handleidingController = handleidingLoader.getController();

	    administratieController.setControllerGoedkeuren(goedkeurenController);
	    administratieController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)administratieScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(goedkeurenView,handleidingView);
        
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
		this.gkC = gk;
	}
	public void setControllerHandleiding(ManualController gk)
	{
		this.hdC = gk;
	}
	public void toonHanleiding()
	{
		this.hdC.openHandleidingMenu();
	}
	public void toonGoedkeurenMenu()
	{
		this.gkC.openGoedkeurenMenu();
	}
	public void downloadCSV()
	{
		this.export.exportCSV();
	}
}

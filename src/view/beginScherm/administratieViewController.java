package view.beginScherm;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.goedkeurenView.goedkeurenController;
import view.handleiding.handleidingController;
import view.home.homeController;

public class administratieViewController {
	
	@FXML private Button btnGoedkeuren;
	@FXML private Pane pane;
	private goedkeurenController gkC;
	private handleidingController hdC;
	
	public void startAdministrator() throws IOException
	{
		Stage primaryStage = new Stage();
		FXMLLoader administratieScherm = new FXMLLoader(getClass().getResource("/view/beginScherm/administratieView.fxml"));	
		BorderPane View  = (BorderPane)administratieScherm.load();
		administratieViewController administratieController = administratieScherm.getController();
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/view/home/home.fxml"));			//get xml file
	    Pane homeView = homeLoader.load();
	    homeController homeController = homeLoader.getController();
	    View.setTop(homeView);
	    
	    
	    FXMLLoader goedkeurenLoader = new FXMLLoader(getClass().getResource("/view/goedkeurenView/goedkeurenView.fxml"));			//get xml file
	    Pane goedkeurenView = goedkeurenLoader.load();	
	    goedkeurenController goedkeurenController = goedkeurenLoader.getController();
	    
	    FXMLLoader handleidingLoader = new FXMLLoader(getClass().getResource("/view/handleiding/handleiding.fxml"));			//get xml file
	    Pane handleidingView = handleidingLoader.load();	
	    handleidingController handleidingController = handleidingLoader.getController();

	    administratieController.setControllerGoedkeuren(goedkeurenController);
	    administratieController.setControllerHandleiding(handleidingController);
	    
	    Pane tabPane = (Pane)administratieScherm.getNamespace().get("pane"); 						//get stackPane from fieldView
        tabPane.getChildren().addAll(goedkeurenView,handleidingView);
        
		Scene scene = new Scene(View);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void setControllerGoedkeuren(goedkeurenController gk)
	{
		this.gkC = gk;
	}
	public void setControllerHandleiding(handleidingController gk)
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
}

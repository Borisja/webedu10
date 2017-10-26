package view.projectView;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ProjectController {
	@FXML Pane pane;
	
	public void openProjectScherm(){
		pane.setVisible(true);
		
	}
	
	public void sluitProjectScherm(){
		pane.setVisible(false);
	}

}

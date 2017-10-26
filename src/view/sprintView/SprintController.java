package view.sprintView;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SprintController  {
	@FXML Pane pane;
	
	public void openSprintmenu(){
		pane.setVisible(true);
		
	}
	
	public void sluitSprintmenu(){
		pane.setVisible(false);
	}
	

}

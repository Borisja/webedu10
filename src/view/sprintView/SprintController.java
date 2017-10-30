package view.sprintView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class SprintController  implements Initializable {
	@FXML Pane pane;
	
	public void openSprintmenu(){
		pane.setVisible(true);
		
	}
	
	public void sluitSprintmenu(){
		pane.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	

}

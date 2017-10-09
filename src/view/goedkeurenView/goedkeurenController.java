package view.goedkeurenView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class goedkeurenController {
	@FXML Pane pane;
	@FXML Button sluitKnop;
	
	public void openGoedkeurenMenu()
	{
		pane.setVisible(true);
	}
	
	public void sluitGoedkeurenMenu()
	{
		pane.setVisible(false);
	}
}

package view.beginScherm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import view.goedkeurenView.goedkeurenController;
import view.handleiding.handleidingController;

public class viewController {
	
	@FXML private Button btnGoedkeuren;
	@FXML private Pane pane;
	private goedkeurenController gkC;
	private handleidingController hdC;
	
	
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

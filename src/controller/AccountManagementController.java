package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AccountManagementController {
	
	@FXML Pane pane;
	@FXML Button accountToevoegen;
	@FXML Button accountVerwijderen;
	@FXML Button accountWijzigen;
	@FXML Button sluitButton;
	
	public void showAccountView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}

}

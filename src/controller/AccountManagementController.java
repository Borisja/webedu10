package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AccountManagementController {
	
	@FXML Pane pane;
	@FXML Button addAccount;
	@FXML Button deleteAccount;
	@FXML Button modifyAccount;
	@FXML Button closeButton;
	
	public void showAccountView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}

}

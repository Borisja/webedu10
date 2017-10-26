package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MedewerkerBeherenController {
	
	@FXML Pane pane;
	@FXML Button medewerkerToevoegen;
	@FXML Button medewerkerVerwijderen;
	private CreateUserController createUserController;
	
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	
	public void showAddEmployee()
	{
		this.pane.setVisible(false);
		this.createUserController.showView();
	}

	public void setControllerCreateUserController(CreateUserController createUserController) {
		this.createUserController = createUserController;
	}

}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * Deze klasse is verantwoordelijk voor de EmployeeManagementView
 * @author rezanaser
 *
 */
public class EmployeeManagementController {
	
	@FXML Pane pane;
	@FXML Button addEmployee;
	@FXML Button deleteEmployee;
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

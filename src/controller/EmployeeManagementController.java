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
	private LockUserController lockUserController;
	private EmployeesOverviewController employeeOverviewController;
	private EditEmployeeController editEmployeeController;
	
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
	
	public void showLockEmployee()
	{
		this.pane.setVisible(false);
		this.lockUserController.showView();
		
	}
	public void showAllEmployee()
	{
		this.pane.setVisible(false);
		this.employeeOverviewController.showEmployeeOverview();
		
	}
	public void showEditEmployee(){
		this.pane.setVisible(false);
		this.editEmployeeController.showEditEmployee();
	}
	/**
	 * Deze methode krijgt de createusercontroller van de administratorviewcontrooler
	 * @param createUserController - > Controller meegekregen van administratorviewcontrooler
	 * @author rezanaser
	 */
	public void setControllerCreateUserController(CreateUserController createUserController) {
		this.createUserController = createUserController;
	}
	/**
	 * Deze methode krijgt de lockUserController van de administratorviewcontrooler
	 * @param lockController - > Controller meegekregen van administratorviewcontrooler
	 * @author rezanaser
	 */
	public void setControllerLockUserController(LockUserController lockController) {
		this.lockUserController = lockController;
		
	}
	
	/**
	 * Deze methode krijgt de employeeOverviewController van de administratorviewcontrooler
	 * @param emController - > Controller meegekregen van administratorviewcontrooler
	 * @author rezanaser
	 */
	public void setControllerEmployee(EmployeesOverviewController emController)
	{
		this.employeeOverviewController = emController;
	}

	/**
	 * Deze methode krijgt de employeeOverviewController van de administratorviewcontroller
	 * @param editEmployeeController
	 * @author Robert den Blaauwen
	 */
	public void setControllerEditEmployeeController(EditEmployeeController editEmployeeController){
		this.editEmployeeController=editEmployeeController;
	}
}

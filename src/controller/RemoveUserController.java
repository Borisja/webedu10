package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AdministratorDAO;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EmployeeModel;

/**
 * @author Boris Janjic
 *
 */

public class RemoveUserController implements Initializable {
	private ObservableList<EmployeeModel> data = FXCollections.observableArrayList();
	private EmployeeDAO employeeDao = new EmployeeDAO();
	private AdministratorDAO administratorDao = new AdministratorDAO();
	
	@FXML
	Button lockButton;
	
	@FXML 
	TableView<EmployeeModel> activeAccountList;
	
	@FXML
	TableColumn<EmployeeModel, Integer> id;
	
	@FXML
	TableColumn<EmployeeModel, String> firstName;

	@FXML
	TableColumn<EmployeeModel, String> lastName;

	@FXML
	TableColumn<EmployeeModel, String> email;
	
	@FXML
	TableColumn<EmployeeModel, String> role;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeFirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeLastName"));
		email.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeEmail"));
		role.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeRole"));
		//employeeDao.activeAccountsList().forEach(e-> System.out.println(e.getEmployeeFirstname()));
	
		//employeeDao.activeAccountsList().forEach(e -> data.add(e));
		data.addAll(employeeDao.activeAccountsList());
		
		activeAccountList.setItems(data);;
		
	}
	

	public void LockUser() {
		//administratorDao.lockEmployee(emp_id);
		
		System.out.println();
	}

}

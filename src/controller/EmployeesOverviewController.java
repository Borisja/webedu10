package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EmployeeModel;

public class EmployeesOverviewController implements Initializable{
	@FXML TableView<EmployeeModel> allEmployees;
	private @FXML TableColumn<EmployeeModel, Integer> iId;
	private @FXML TableColumn<EmployeeModel, String> firstName;
	private @FXML TableColumn<EmployeeModel, String> lastName;
	private @FXML TableColumn<EmployeeModel, String> email;
	private @FXML TableColumn<EmployeeModel, String> password;
	private @FXML TableColumn<EmployeeModel, String> role;
	private @FXML TableColumn<EmployeeModel, String> status;
	private @FXML Pane pane;
	private EmployeeDAO employeeDao = new EmployeeDAO();
	private ObservableList<EmployeeModel> data = FXCollections.observableArrayList();
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iId.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("employeeId"));
		firstName.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeFirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeLastName"));
		email.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeEmail"));
		password.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeePassword"));
		role.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeRole"));
		status.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeIsDeleted"));
		
		data.addAll(employeeDao.getAllEmployees());
		allEmployees.setItems(data);


		
	}
	
	public void showEmployeeOverview()
	{
		this.pane.setVisible(true);
	}
	
	public void hideView()
	{
		this.pane.setVisible(false);
	}

}

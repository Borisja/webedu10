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
import javafx.scene.layout.Pane;
import model.EmployeeModel;

/**
 * @author Boris Janjic
 *
 */

public class LockUserController implements Initializable {
	private ObservableList<EmployeeModel> lockedAccounts = FXCollections.observableArrayList();
	private ObservableList<EmployeeModel> unlockedAccounts = FXCollections.observableArrayList();
	private EmployeeDAO employeeDao = new EmployeeDAO();
	private AdministratorDAO administratorDao = new AdministratorDAO();
	
	@FXML
	Button lockButton;	
	@FXML
	Button unlockButton;
	
	@FXML 
	TableView<EmployeeModel> activeAccountList;	
	@FXML
	TableView<EmployeeModel> lockedAccountsList;
	
	@FXML
	TableColumn<EmployeeModel, Integer> idUnlocked;
	@FXML
	TableColumn<EmployeeModel, Integer> idLocked;	
	
	@FXML
	TableColumn<EmployeeModel, String> firstNameUnlocked;
	@FXML
	TableColumn<EmployeeModel, String> firstNameLocked;

	@FXML
	TableColumn<EmployeeModel, String> lastNameUnlocked;
	@FXML
	TableColumn<EmployeeModel, String> lastNameLocked;

	@FXML
	TableColumn<EmployeeModel, String> emailUnlocked;
	@FXML
	TableColumn<EmployeeModel, String> emailLocked;	
	
	@FXML
	TableColumn<EmployeeModel, String> roleUnlocked;
	@FXML
	TableColumn<EmployeeModel, String> roleLocked;
	
	@FXML 
	Pane pane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		idUnlocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("employeeId"));
		firstNameUnlocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeFirstName"));
		lastNameUnlocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeLastName"));
		emailUnlocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeEmail"));
		roleUnlocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeRole"));
		
		unlockedAccounts.addAll(employeeDao.activeAccountsList());
		
		activeAccountList.setItems(unlockedAccounts);
		

		idLocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("employeeId"));
		firstNameLocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeFirstName"));
		lastNameLocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeLastName"));
		emailLocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeEmail"));
		roleLocked.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeRole"));
		
		lockedAccounts.addAll(employeeDao.lockedAccountsList());
		
		lockedAccountsList.setItems(lockedAccounts);
	}
	

	public void lockUser() {
		int id = activeAccountList.getSelectionModel().getSelectedItem().getEmployeeId();
		
		if(id >=0) {
			administratorDao.lockEmployee(id);
			lockedAccountsList.refresh();
			activeAccountList.refresh();
		}
	}
	
	public void unLockUser() {
		int id = lockedAccountsList.getSelectionModel().getSelectedItem().getEmployeeId();

		if(id >=0) {
			administratorDao.unlockEmployee(id);
			lockedAccountsList.refresh();
			activeAccountList.refresh();
		}
	}
	
	public void tableRefresh() {
				
	}
	
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}

}

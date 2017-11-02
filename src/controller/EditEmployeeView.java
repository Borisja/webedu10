package controller;

import dao.AdministratorDAO;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EmployeeModel;

import java.util.ArrayList;

/**
 * Created by Robert on 10/31/2017.
 */
public class EditEmployeeView {
    private ObservableList<EmployeeModel> accounts = FXCollections.observableArrayList();
    private EditEmployeeController editEmployeeController;

    @FXML
    Pane mainPane;
    @FXML
    Pane fieldPane;

    @FXML
    TableView<EmployeeModel> activeAccountList;
    @FXML
    TableColumn<EmployeeModel, Integer> idColumn;

    @FXML
    TableColumn<EmployeeModel, String> firstNameColumn;

    @FXML
    TableColumn<EmployeeModel, String> lastNameColumn;

    @FXML
    TableColumn<EmployeeModel, String> emailColumn;

    @FXML
    TableColumn<EmployeeModel, String> roleColumn;

    @FXML
    Label firstNameLabel;
    @FXML
    TextField firstNameText;

    @FXML Label lastNameLabel;
    @FXML TextField lastNameText;

    @FXML Label emailLabel;
    @FXML TextField emailText;

    @FXML
    ComboBox<String> roleCombo;
    @FXML
    TextField passwordText;
    @FXML
    Button createButton;
    @FXML Label errorLabel;

    ObservableList<String> roleList = FXCollections.observableArrayList("Employee","Administration","Manager");

    EmployeeModel selectedUser;

    @FXML
    private void initialize() {
        mainPane.setVisible(true);
        roleCombo.setValue("Employee");
        roleCombo.setItems(roleList);
    }
    public void setEditEmployeeController(EditEmployeeController editEmployeeController) {
        this.editEmployeeController = editEmployeeController;
    }
    public Pane getMainPane() {
        return mainPane;
    }
    public void fillTable(ArrayList<EmployeeModel> accountsList) {

        activeAccountList.getItems().clear();

        idColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeFirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeLastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeEmail"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<EmployeeModel, String>("employeeRole"));

        accounts.addAll(accountsList);

        activeAccountList.setItems(accounts);
    }
    public void selectUser(){
        selectedUser = activeAccountList.getSelectionModel().getSelectedItem();
        firstNameText.setText(selectedUser.getEmployeeFirstname());
        lastNameText.setText(selectedUser.getEmployeeLastName());
        emailText.setText(selectedUser.getEmployeeEmail());
        roleCombo.setValue(selectedUser.getEmployeeRole());
        passwordText.setText("");
        fieldPane.setVisible(true);
    }
    public void editEmployee(){
        selectedUser.setEmployeeFirstname(firstNameText.getText().trim());
        selectedUser.setEmployeeLastname(lastNameText.getText().trim());
        selectedUser.setEmployeeRole(roleCombo.getValue().trim().toLowerCase());
        selectedUser.setEmployeeEmail(emailText.getText().trim().toLowerCase());
        if(passwordText.getText()!=null&&passwordText.getText().trim()!=""){
            selectedUser.setEmployeePassword(passwordText.getText().trim());
        }
        this.editEmployeeController.editEmployee(selectedUser);
    }
    public void closeView(){
        mainPane.setVisible(false);
    }
}

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.CreateUserModel;

/**
 * @author Boris Janjic
 *
 */

public class CreateUserController {
	
	@FXML Label firstNameLabel;
	@FXML TextField firstNameText;
	@FXML Pane pane;
	
	@FXML Label lastNameLabel;
	@FXML TextField lastNameText;
	
	@FXML Label emailLabel;
	@FXML TextField emailText;
	
	@FXML ComboBox<String> roleCombo;	
	@FXML Button createButton;
	@FXML Label errorLabel;
	
	ObservableList<String> roleList = FXCollections.observableArrayList("Employee","Administration","Manager");
	
	@FXML
	private void initialize() {
		roleCombo.setValue("Employee");
		roleCombo.setItems(roleList);
	}
	/**
	 * Deze methode open the view
	 * @author rezanaser
	 */
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	public void createUser() {
		
		String firstName = firstNameText.getText();
		String lastName = lastNameText.getText();
		String email = emailText.getText();
		String Role = roleCombo.getValue();
		
		String password = "1234";
		
		if(checkFirstName(firstName) == true & checkLastName(lastName) == true & checkEmail(email) == true) {

			setLabelToDefaultColor(errorLabel);
			errorLabel.setText("");
			
			CreateUserModel createUserModel = new CreateUserModel(firstName, lastName, email, password, Role.toLowerCase());
			createUserModel.createUser();
			
		} else {
			setLabelToErrorColor(errorLabel);
			errorLabel.setText("Alle velden moeten ingevuld zijn.");
		}
		
		
	}
	
	public boolean checkFirstName(String firstName) {		
		if(firstName.isEmpty()) {
			setLabelToErrorColor(firstNameLabel);
			return false;
		} else {
			setLabelToDefaultColor(firstNameLabel);
			return true;
		}		
	}
	
	public boolean checkLastName(String lastName) {		
		if(lastName.isEmpty()) {
			setLabelToErrorColor(lastNameLabel);
			return false;
		} else {
			setLabelToDefaultColor(lastNameLabel);
			return true;
		}		
	}
	
	public boolean checkEmail(String email) {		
		if(email.isEmpty()) {
			setLabelToErrorColor(emailLabel);
			return false;
		} else {
			setLabelToDefaultColor(emailLabel);
			return true;
		}		
	}
	
	public void setLabelToErrorColor(Label label) {
		label.setTextFill(Color.web("red"));
	}
	
	public void setLabelToDefaultColor(Label label) {
		label.setTextFill(Color.web("black"));
	}
}
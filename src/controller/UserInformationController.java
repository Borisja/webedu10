package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EmployeeDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.EmployeeModel;

public class UserInformationController implements Initializable{
	@FXML Button close;
	@FXML TextField textfieldFirstName;
	@FXML TextField textfieldLastName;
	@FXML TextField textFieldEmail;
	@FXML Pane pane;
	
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	
	public void updateInformation()
	{
		this.textfieldLastName.setEditable(true);
		this.textfieldFirstName.setEditable(true);
		this.textFieldEmail.setEditable(true);
	}
	
	public void fillUserData(EmployeeModel em)
	{
		this.textfieldFirstName.setText(em.getEmployeeFirstname());
		this.textfieldLastName.setText(em.getEmployeeLastName());
		this.textFieldEmail.setText(em.getEmployeeEmail());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.textfieldLastName.setEditable(false);
		this.textfieldFirstName.setEditable(false);
		this.textFieldEmail.setEditable(false);
		
	}
	

}

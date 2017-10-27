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
	@FXML TextField txt_firstName;
	@FXML TextField txt_lastName;
	@FXML TextField txt_email;
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
		this.txt_lastName.setEditable(true);
		this.txt_firstName.setEditable(true);
		this.txt_email.setEditable(true);
	}
	
	public void fillUserData(EmployeeModel em)
	{
		this.txt_firstName.setText(em.getEmployeeFirstname());
		this.txt_lastName.setText(em.getEmployeeAchternaam());
		this.txt_email.setText(em.getEmployeeEmail());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.txt_lastName.setEditable(false);
		this.txt_firstName.setEditable(false);
		this.txt_email.setEditable(false);
		
	}
	

}

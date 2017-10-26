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

public class GebruikerGegevensController implements Initializable{
	@FXML Button sluit;
	@FXML TextField txt_voornaam;
	@FXML TextField txt_achternaam;
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
	
	public void updateGegevens()
	{
		this.txt_achternaam.setEditable(true);
		this.txt_voornaam.setEditable(true);
		this.txt_email.setEditable(true);
	}
	
	public void fillUserData(EmployeeModel em)
	{
		this.txt_voornaam.setText(em.getEmployeeVoornaam());
		this.txt_achternaam.setText(em.getEmployeeAchternaam());
		this.txt_email.setText(em.getEmployeeEmail());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.txt_achternaam.setEditable(false);
		this.txt_voornaam.setEditable(false);
		this.txt_email.setEditable(false);
		
	}
	

}

package controller;

import javax.swing.JComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import model.CustomerModel;

public class DeclareEntryController 
{
	
	@FXML ComboBox<String> userStoryCB;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	
	@FXML 
	public void init()
	{
		userStoryCB = new ComboBox<String>();
		userStoryCB.getItems().add("1");
	}

	
}

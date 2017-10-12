package controller;

import javax.swing.JComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DeclareEntryController 
{
	@FXML Label startTimeLabel;
	@FXML Label endTimeLabel;
	@FXML Label projectLabel;
	@FXML Label sprintLabel;
	@FXML Label userStoryLabel;
	@FXML Label descriptionLabel;	
	
	@FXML ComboBox<Hour> startTimeHourComboBox;
	@FXML ComboBox startTimeMinuteComboBox;
	@FXML ComboBox endTimeHourComboBox;
	@FXML ComboBox endTimeMinuteComboBox;
	@FXML ComboBox projectComboBox;
	@FXML ComboBox sprintComboBox;
	@FXML ComboBox userStoryComboBox;
	@FXML TextArea descriptionTextArea;
	
	public void init()
	{
		startTimeHourComboBox.getItems().removeAll();
		startTimeHourComboBox.getItems().add(new Hour(1));
	}
	
	

	
	

	
}

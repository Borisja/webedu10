package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
	
	@FXML ComboBox<int[]> startTimeHourComboBox;
	@FXML ComboBox startTimeMinuteComboBox;
	@FXML ComboBox endTimeHourComboBox;
	@FXML ComboBox endTimeMinuteComboBox;
	@FXML ComboBox projectComboBox;
	@FXML ComboBox sprintComboBox;
	@FXML ComboBox userStoryComboBox;
	@FXML TextArea descriptionTextArea;
	
	

	public void fillComboBoxes()
	{
		
		int[] hours = {1,2,3,4,5,6,7,8};
		startTimeHourComboBox.getItems().add(hours);


		System.out.println("Done adding.");
		
	}
	
}

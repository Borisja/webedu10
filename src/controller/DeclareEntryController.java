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

public class DeclareEntryController 
{
	int startTimeHour;
	int startTimeMinute;
	int endTimeHour;
	int endTimeMinute;
	String project;
	String sprint;
	String userStory;
	String description;
	
	@FXML Label startTimeLabel;
	@FXML Label endTimeLabel;
	@FXML Label projectLabel;
	@FXML Label sprintLabel;
	@FXML Label userStoryLabel;
	@FXML Label descriptionLabel;	
	
	@FXML 
	private ChoiceBox startTimeHourChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(
		    "First", "Second", "Third"));
	
	
	@FXML Button save;
	@FXML Button cancel;
	
	ObservableList<String> mainDepartmentList = FXCollections.observableArrayList("Electrical", "Mechanical");
	
	public void init()
	{
	}
	
	public void saveEntry()
	{
		
	}
	
	

	
	

	
}

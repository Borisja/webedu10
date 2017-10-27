package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddHoursController implements Initializable{
	@FXML Label project_label;
	@FXML Label sprint_label;
	@FXML Label	user_story_label;
	@FXML Label project_name;
	@FXML Label sprint_name;
	@FXML Label	user_story_name;
	@FXML Label start_time;
	@FXML Label end_time;
	
	@FXML TextField txt_start_time;
	@FXML TextField txt_end_time;
	
	@FXML Button btn_add;
	
	public void loadLabels(String project_name, String sprint_name, String user_story_name) {
		this.project_name.setText(project_name);
		this.sprint_name.setText(sprint_name);
		this.user_story_name.setText(user_story_name);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//		this.loadLabels();
	}
}

package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.EntryModel;

public class EntryHoursOverview {
	Label lbl_description, lbl_start_time, lbl_end_time;
	TextField txt_start_time, txt_end_time;
	GridPane grid;
	Button btn_change, btn_back;
	
	public void EntryOverviewShow(EntryModel em) {
		Stage entry_edit_stage = new Stage();
		Scene entry_edit_scene;
		grid = new GridPane();
		lbl_description = new Label("Entry Description: " + em.getEntryDescription());
		lbl_start_time = new Label("Start Time: ");
		lbl_end_time = new Label("End Time: ");
		
		btn_change = new Button("Change Workhours");
		btn_back = new Button("Back");
		
		txt_start_time = new TextField();
		txt_end_time = new TextField();
		
		grid.add(lbl_description, 0, 0);
		grid.add(lbl_start_time, 0, 1);
		grid.add(lbl_end_time, 0, 2);
		grid.add(txt_start_time, 1, 1);
		grid.add(txt_end_time, 1, 2);
		grid.add(btn_change, 0, 3);
		grid.add(btn_back, 0, 4);
		
		txt_start_time.setText(em.getEntryStartTime());
		txt_end_time.setText(em.getEntryEndTime());
		
		entry_edit_scene = new Scene(grid, 400, 400);
		
		entry_edit_stage.setScene(entry_edit_scene);
		entry_edit_stage.show();
	}
}

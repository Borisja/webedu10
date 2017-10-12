package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.EntryModel;

public class AgendaView {
	private ObservableList<EntryModel> data;
    private TableView<EntryModel> table = new TableView<EntryModel>();
    Button btn_edit;
	
	@SuppressWarnings("unchecked")
	public void agendaShow(EmployeeModel em) throws ParseException {
		Stage agenda_stage = new Stage();
		GridPane grid = new GridPane();
		EmployeeDAO edao = new EmployeeDAO();
		
		data = FXCollections.observableArrayList();
		
		TableColumn<EntryModel, String> EntryNameCol = new TableColumn<EntryModel, String>("Entry Description");
		EntryNameCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryDescription"));
		EntryNameCol.setMinWidth(100);
        
		TableColumn<EntryModel, String> EntryStartCol = new TableColumn<EntryModel, String>("Entry Start Time");
		EntryStartCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		EntryStartCol.setMinWidth(100);
		
		TableColumn<EntryModel, String> EntryStopCol = new TableColumn<EntryModel, String>("Entry Stop Time");
		EntryStopCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		EntryStopCol.setMinWidth(100);
		
		
        table.getColumns().addAll(EntryNameCol, EntryStartCol, EntryStopCol);
        
        edao.entry_list(1).forEach(e-> data.add(e));

        table.setItems(data);
        
        btn_edit = new Button("Edit Hours");
        
        grid.add(table, 0, 0);
        grid.add(btn_edit, 1, 0);

        btn_edit.setOnAction(e->{
        	EntryModel selected_entry = table.getSelectionModel().getSelectedItem();
        	new EntryHoursOverview().EntryOverviewShow(selected_entry);
        });
        
		Scene agenda_scene = new Scene(grid, 600, 400);
		agenda_stage.setScene(agenda_scene);
		agenda_stage.show();
		
	}
}

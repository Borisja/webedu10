package view.goedkeurenView;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import dao.AdministratorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EntryModel;

public class goedkeurenController implements Initializable{
	@FXML Pane pane;
	@FXML Button sluitKnop;
	@FXML TableView<EntryModel> tableView;
	@FXML TableColumn<EntryModel, Integer> iId;
	@FXML TableColumn<EntryModel, String> iDescription;
	@FXML TableColumn<EntryModel, String> iStartTime;
	@FXML TableColumn<EntryModel, String> iEndTime;
	
	private ObservableList<EntryModel> data = FXCollections.observableArrayList();
	
	private AdministratorDAO adminDao = new AdministratorDAO();
	
	public void openGoedkeurenMenu()
	{
		pane.setVisible(true);
	}
	
	public void sluitGoedkeurenMenu()
	{
		pane.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		EntryModel entry = new EntryModel();
		iId.setCellValueFactory(new PropertyValueFactory<EntryModel, Integer>("entry_id"));
		iDescription.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryDescription"));
		iStartTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		iEndTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		
		
		data.addAll(adminDao.entry_list(1));
		
		tableView.setItems(data);
	}
}

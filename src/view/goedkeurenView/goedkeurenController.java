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
	@FXML Button approve;
	@FXML Button reject;
	@FXML TableView<EntryModel> tableView;
	@FXML TableColumn<EntryModel, Integer> iId;
	@FXML TableColumn<EntryModel, String> iDescription;
	@FXML TableColumn<EntryModel, String> iStartTime;
	@FXML TableColumn<EntryModel, String> iEndTime;
	
	private ObservableList<EntryModel> data = FXCollections.observableArrayList();
	
	private AdministratorDAO adminDao = new AdministratorDAO();
	
	/**
	 * Deze methode keurt de geselcteerde uur goed
	 * @author rezanaser
	 */
	public void approveSelectedHour()
	{
		EntryModel selected_item = tableView.getSelectionModel().getSelectedItem();
		adminDao.approveHours(selected_item.getEntry_id());
	}
	
	/**
	 * Deze methode keurt de geselcteerde uur af
	 * @author rezanaser
	 */
	public void rejectSelectedHour()
	{
		EntryModel selected_item = tableView.getSelectionModel().getSelectedItem();
		adminDao.rejectHours(selected_item.getEntry_id());
	}
	
	/**
	 * Deze methode opent de goedkeuren view nadat er geklickt word
	 * @author rezanaser
	 */
	public void openGoedkeurenMenu()
	{
		pane.setVisible(true);
	}
	
	/**
	 * Deze methode sluit de view nadat er geklickt wordt op de sluit button.
	 * @author rezanaser
	 */
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
		
		data.addAll(adminDao.entry_queued_list(0));
		
		
		tableView.setItems(data);
	}
}

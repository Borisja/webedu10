package view.approvalView;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import dao.AdministratorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EntryModel;

public class ApprovalController implements Initializable{
	@FXML Pane pane;
	@FXML Button closeButton;
	@FXML Button approveButton;
	@FXML Button rejectButton;
	@FXML Button refreshButton;
	@FXML TableView<EntryModel> tableView;
	@FXML TableColumn<EntryModel, Integer> iId;
	@FXML TableColumn<EntryModel, String> iDescription;
	@FXML TableColumn<EntryModel, String> iStartTime;
	@FXML TableColumn<EntryModel, String> iEndTime;
	@FXML Label notSelected;
	
	private ObservableList<EntryModel> data = FXCollections.observableArrayList();
	
	private AdministratorDAO adminDao = new AdministratorDAO();
	
	/**
	 * Deze methode keurt de geselcteerde uur goed
	 * @author rezanaser
	 */
	public void approveSelectedHour()
	{
		
		try{
			EntryModel selected_item = tableView.getSelectionModel().getSelectedItem();
			adminDao.approveHours(selected_item.getEntryId());
			refreshTable();
		}catch(NullPointerException e)
		{
			notSelected.setText("Select an hour");
		}
	}
	
	/**
	 * Deze methode keurt de geselcteerde uur af
	 * @author rezanaser
	 */
	public void rejectSelectedHour()
	{
		try{
			EntryModel selected_item = tableView.getSelectionModel().getSelectedItem();
			adminDao.rejectHours(selected_item.getEntryId());
			refreshTable();
		}catch(NullPointerException e)
		{
			notSelected.setText("Select an hour");
		}
	}
	
	/**
	 * Deze methode opent de goedkeuren view nadat er geklickt word
	 * @author rezanaser
	 */
	public void openApprovalMenu()
	{
		pane.setVisible(true);
	}
	
	/**
	 * Deze methode sluit de view nadat er geklickt wordt op de sluit button.
	 * @author rezanaser
	 */
	public void closeApprovalMenu()
	{
		pane.setVisible(false);
	}

	/**
	 * Deze methode word uitgevoerd wanneer er op refresh knop wordt gedrukt.
	 * Bijwerken lijst
	 * @author rezanaser
	 */
	
	public void refreshTable()
	{
		tableView.getItems().clear();
		iId.setCellValueFactory(new PropertyValueFactory<EntryModel, Integer>("entry_id"));
		iDescription.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryDescription"));
		iStartTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		iEndTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		data.addAll(adminDao.entry_queued_list(0));
		tableView.setItems(data);
		tableView.refresh();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iId.setCellValueFactory(new PropertyValueFactory<EntryModel, Integer>("entry_id"));
		iDescription.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryDescription"));
		iStartTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		iEndTime.setCellValueFactory(new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		
		data.addAll(adminDao.entry_queued_list(0));
		tableView.setItems(data);
	}
}

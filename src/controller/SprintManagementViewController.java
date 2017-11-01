package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import dao.SprintDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.ProjectModel;
import model.SprintModel;

public class SprintManagementViewController implements Initializable
{

	@FXML TableView<SprintModel> sprintTableView;
	@FXML TableColumn<SprintModel, Integer> sprintID;
	@FXML TableColumn<SprintModel, String> sprintName;
	@FXML TableColumn<SprintModel, String> sprintDescription;
	@FXML TableColumn<SprintModel, String> sprintStartDate;
	@FXML TableColumn<SprintModel, String> sprintEndDate;
	@FXML TableColumn<SprintModel, String> projectName;
	@FXML TableColumn<SprintModel, String> sprintIsDeleted;
	
	@FXML ComboBox<SprintModel> addProjectComboBox;
	@FXML ComboBox<SprintModel> changeProjectComboBox;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label projectIDLabel;
	@FXML TextField changeSprintNameTextField;
	@FXML TextField changeSprintDescriptionTextField;
	@FXML DatePicker changeSprintStartDateDatePicker;
	@FXML DatePicker changeSprintEndDateDatePicker;
	@FXML TextField newSprintNameTextField;
	@FXML TextField newSprintDescriptionTextField;
	@FXML DatePicker newSprintStartDateDatePicker;
	@FXML DatePicker newSprintEndDateDatePicker;
	@FXML Button changeSprintButton;
	@FXML Button addSprintButton;
	@FXML Button deleteSprintButton;
	@FXML Label warningLabel;
	@FXML Button button;
	
	private ObservableList<SprintModel> allSprints = FXCollections.observableArrayList(); 
	private SprintDAO sprintDAO = new SprintDAO();
	private int selectedSprintID;
	
	/**
	 * Deze methode zorgt ervoor dat er in de combobox de naam van het project komt te staan
	 * in plaats van het volledige object.
	 * @author Jeroen Zandvliet
	 */

	public void fillProjectBox()
	{	
		ArrayList<SprintModel> pList = new SprintDAO().sprint_list();
		
		ObservableList<SprintModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		
		Callback<ListView<SprintModel>, ListCell<SprintModel>> factory = lv -> new ListCell<SprintModel>() {
		    @Override
		    protected void updateItem(SprintModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getSprintName());
		    }

		};
		
		this.addProjectComboBox.setItems(data);
		this.addProjectComboBox.setCellFactory(factory);
		this.addProjectComboBox.setButtonCell(factory.call(null));
		this.changeProjectComboBox.setItems(data);
		this.changeProjectComboBox.setCellFactory(factory);
		this.changeProjectComboBox.setButtonCell(factory.call(null));
		
	}


	/**
	 * Deze methode toont de popup waar de gebruiker een Sprint kan veranderen.
	 * @author Jeroen Zandvliet
	 */
	public void showPopUpChange() 
	{
		try{
			popUp.setVisible(true);
			
			SprintModel selectedItem = sprintTableView.getSelectionModel().getSelectedItem();
			this.selectedSprintID = selectedItem.getSprintId();
//			changeProjectComboBox.setValue();
			changeSprintNameTextField.setText(selectedItem.getSprintName());
			changeSprintDescriptionTextField.setText(selectedItem.getSprintDescription());
//			changeSprintEndDateDatePicker.setValue(String.valueOf(selectedItem.getSprintStartDate()));
			changeSprintEndDateDatePicker.setValue(null);
			
		}
		catch(NullPointerException e)
		{
			Alert showMessage = new Alert(AlertType.INFORMATION);
			showMessage.setTitle("Niets geselecteerd");
			showMessage.setContentText("Selecteer alles om verder te gaan ");
			showMessage.showAndWait();
		}
		
	}
	
	/**
	 * Deze methode roept de SprintDAO aan om een sprint in de database toe te voegen.
	 * @author Jeroen Zandvliet
	 * @throws SQLException
	 */
	public void addSprint() throws SQLException
	{
		try
		{
		Date sprintStartDate = Date.valueOf(newSprintStartDateDatePicker.getValue());
		Date sprintEndDate = Date.valueOf(newSprintEndDateDatePicker.getValue());
		new SprintDAO().addSprintToDatabase(addProjectComboBox.getSelectionModel().getSelectedItem().getSprintId(), newSprintNameTextField.getText(), newSprintDescriptionTextField.getText(), sprintStartDate , sprintEndDate);
		refreshTable();
		clearAllFields();
		
		closePopupAdd();
		}
		catch(NullPointerException e)
		{
			Alert showMessage = new Alert(AlertType.INFORMATION);
			showMessage.setTitle("U heeft iets niet geselecteerd");
			showMessage.setContentText("Selecteer iets om verder te gaan ");
			showMessage.showAndWait();
		}
		
	}
	
	
	
	/**
	 * Deze methode roept de SprintDAO aan om een sprint in de database te modificeren.
	 * @author Jeroen Zandvliet
	 */
	public void modifySprint() 
	{		
		
			try{
				
				Date startDate = Date.valueOf(changeSprintStartDateDatePicker.getValue());
				Date endDate = Date.valueOf(changeSprintEndDateDatePicker.getValue());
				
				sprintDAO.modifySprint(this.selectedSprintID, changeSprintNameTextField.getText(), changeProjectComboBox.getSelectionModel().getSelectedItem().getSprintId(), changeSprintDescriptionTextField.getText(), startDate, endDate);
				
				closePopup();
			}
			
			catch(NullPointerException e)
			{
				warningLabel.setText("Selecteer iets ");
			}
		clearAllFields();
		refreshTable();
		}


	/**
	 * Deze methode opent de popup die de gebruiker Sprints laat toevoegen.
	 * @author Jeroen Zandvliet
	 */
	public void showPopUpAdd() 
	{
		popUpAdd.setVisible(true);
	}
	
	/**
	 * Deze methode verwijderd de sprint dat op dit moment geselecteerd is.
	 * @author Jeroen Zandvliet
	 */
	public void removeSelectedSprint()
	{
		SprintModel selected_item = sprintTableView.getSelectionModel().getSelectedItem();
		sprintDAO.removeSprint(selected_item.getSprintId());
		clearAllFields();
		refreshTable();
	}
	
	/**
	 * Deze methode zorgt ervoor dat de tabel ververst wordt.
	 * @author Jeroen Zandvliet
	 */
	public void refreshTable()
	{
		sprintTableView.getItems().clear();
		sprintID.setCellValueFactory(new PropertyValueFactory<SprintModel, Integer>("sprintId"));
		sprintName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintName"));
		sprintDescription.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintDescription"));
				
		sprintStartDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintStartDate"));
		sprintEndDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintEndDate"));
		projectName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("projectName"));
		
		
		allSprints.addAll(sprintDAO.sprint_list());
		sprintTableView.setItems(allSprints);
		sprintTableView.refresh();
	}
	
	
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	
	public void closePopup()
	{
		this.popUp.setVisible(false);
	}
	
	public void closePopupAdd()
	{
		this.popUpAdd.setVisible(false);
	}
	
	

	/**
	 * Deze methode initialiseert de TableView
	 * @author Jeroen Zandvliet
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.fillProjectBox();//Vult customer box in met klantnamen
		sprintID.setCellValueFactory(new PropertyValueFactory<SprintModel, Integer>("sprintId"));
		sprintName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintName"));
		sprintDescription.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintDescription"));
		sprintIsDeleted.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintIsDeleted"));
		
		sprintStartDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintStartDate"));
		sprintEndDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintEndDate"));
		projectName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("projectName"));
		
		allSprints.addAll(sprintDAO.sprint_list());
		sprintTableView.setItems(allSprints);
	}
	
	/**
	 * Deze methode maakt alle invulbare velden leeg.
	 * @author Jeroen Zandvliet
	 */
	public void clearAllFields()
	{
		changeSprintNameTextField.setText("");
		changeSprintDescriptionTextField.setText("");
		changeSprintStartDateDatePicker.setValue(null);
		changeSprintEndDateDatePicker.setValue(null);
		changeProjectComboBox.setValue(null);
		
		newSprintNameTextField.setText("");
		newSprintDescriptionTextField.setText("");
		newSprintStartDateDatePicker.setValue(null);
		newSprintEndDateDatePicker.setValue(null);
		addProjectComboBox.setValue(null);
	}
}

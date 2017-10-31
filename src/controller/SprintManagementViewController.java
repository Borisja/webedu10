package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.CustomerDAO;
import dao.ProjectDAO;
import dao.SprintDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import model.CustomerModel;
import model.ProjectModel;
import model.SprintModel;

public class SprintManagementViewController implements Initializable
{

	@FXML TableView<SprintModel> sprintTableView;
	@FXML TableColumn<SprintModel, Integer> sprintID;
	@FXML TableColumn<SprintModel, String> sprintName;
	@FXML TableColumn<SprintModel, String> sprintDescription;
	@FXML TableColumn<SprintModel, String> sprintIsDeleted;
	@FXML ComboBox<ProjectModel> projectComboBox;
	@FXML ComboBox<ProjectModel> projectComboBox1;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label projectIDLabel;
	@FXML TextField changeSprintNameTextField;
	@FXML TextField changeSprintDescriptionTextField;
	@FXML TextField changeSprintStartDateTextField;
	@FXML TextField changeSprintEndDateTextField;	
	@FXML TextField newSprintNameTextField;
	@FXML TextField newSprintDescriptionTextField;
	@FXML TextField sprintStartDateTextField;
	@FXML TextField sprintEndDateTextField;
	@FXML Button changeSprintButton;
	@FXML Button addSprintButton;
	@FXML Button deleteSprintButton;
	@FXML Label warningLabel;
	@FXML Button button;
	
	private ObservableList<SprintModel> allSprints = FXCollections.observableArrayList(); 
	private SprintDAO sprintDAO = new SprintDAO();
	private ProjectDAO projectDAO = new ProjectDAO();
	
	

	public void fillProjectBox()
	{	
		ArrayList<ProjectModel> pList = new ProjectDAO().project_list();
		
		ObservableList<ProjectModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		/**
		 * Change what you see in the combobox, to the description rather then the object address.
		 * Must study this to understand. works for now.
		 */
		Callback<ListView<ProjectModel>, ListCell<ProjectModel>> factory = lv -> new ListCell<ProjectModel>() {
		    @Override
		    protected void updateItem(ProjectModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getProjectName());
		    }

		};
		
		this.projectComboBox.setItems(data);
		this.projectComboBox.setCellFactory(factory);
		this.projectComboBox.setButtonCell(factory.call(null));
		this.projectComboBox1.setItems(data);
		this.projectComboBox1.setCellFactory(factory);
		this.projectComboBox1.setButtonCell(factory.call(null));
		
	}


	public void showPopUpChange() 
	{
		try{
			SprintModel selectedItem = sprintTableView.getSelectionModel().getSelectedItem();
			projectIDLabel.setText(String.valueOf(selectedItem.getSprintId()));
			newSprintNameTextField.setText(selectedItem.getSprintName());
			newSprintDescriptionTextField.setText(selectedItem.getSprintDescription());
			popUp.setVisible(true);
		}
		catch(NullPointerException e)
		{
			Alert showMessage = new Alert(AlertType.INFORMATION);
			showMessage.setTitle("Niets geselecteerd");
			showMessage.setContentText("Selecteer iets om verder te gaan ");
			showMessage.showAndWait();
		}
		
	}
	
	public void addSprint()
	{
		Date sprintStartDate = Date.valueOf(sprintStartDateTextField.getText());
		Date sprintEndDate = Date.valueOf(sprintEndDateTextField.getText());
		new SprintDAO().addSprintToDatabase(newSprintNameTextField.getText(), projectComboBox.getSelectionModel().getSelectedItem().getProjectId(), newSprintDescriptionTextField.getText(), sprintStartDate , sprintEndDate);
	}
	
	
	
	public void modifySprint() 
		{
	//		int sprintID = Integer.parseInt(projectIDLabel.getText());
	//		try{
	//			sprintDAO.modifySprint(sprintID, changeSprintNameTextField.getText(), projectComboBox1.getSelectionModel().getSelectedItem().getProjectId(), sprintDescriptionTextField.getText(), sprintStartDateTextField.getText(), sprintEndDateTextField.getText());
	//			refreshTable();
	//			closePopup();
	//		}
	//		
	//		catch(NullPointerException e)
	//		{
	//			lblWarning.setText("Selecteer iets ");
	//		}
		}


	public void showPopUpAdd() 
	{
		popUpAdd.setVisible(true);
	}
	
	/**
	 * Deze methode verwijderd de sprint dat op dit moment geselecteerd is.
	 * @author Jeroen Zandvliet
	 */
	public void removeSelectedProject()
	{
		SprintModel selected_item = sprintTableView.getSelectionModel().getSelectedItem();
		sprintDAO.removeSprint(selected_item.getSprintId());
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
		sprintIsDeleted.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintIsDeleted"));
		
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
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.fillProjectBox();//Vult customer box in met klantnamen
		sprintID.setCellValueFactory(new PropertyValueFactory<SprintModel, Integer>("sprintId"));
		sprintName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintName"));
		sprintDescription.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintDescription"));
		sprintIsDeleted.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintIsDeleted"));
		
		allSprints.addAll(sprintDAO.sprint_list());
		sprintTableView.setItems(allSprints);
	}
}
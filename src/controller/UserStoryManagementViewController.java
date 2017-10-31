package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import dao.UserStoryDAO;
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
import model.UserStoryModel;

public class UserStoryManagementViewController implements Initializable
{

	@FXML TableView<UserStoryModel> userStoryTableView;
	@FXML TableColumn<UserStoryModel, Integer> userStoryID;
	@FXML TableColumn<UserStoryModel, String> userStoryName;
	@FXML TableColumn<UserStoryModel, String> userStoryDescription;
	@FXML TableColumn<UserStoryModel, String> userStoryStartDate;
	@FXML TableColumn<UserStoryModel, String> userStoryEndDate;
	@FXML TableColumn<UserStoryModel, String> projectName;
	@FXML TableColumn<UserStoryModel, String> userStoryIsDeleted;
	
	@FXML ComboBox<ProjectModel> addProjectComboBox;
	@FXML ComboBox<ProjectModel> changeProjectComboBox;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label projectIDLabel;
	@FXML TextField changeUserStoryNameTextField;
	@FXML TextField changeUserStoryDescriptionTextField;
	@FXML DatePicker changeUserStoryStartDateDatePicker;
	@FXML DatePicker changeUserStoryEndDateDatePicker;
	@FXML TextField newUserStoryNameTextField;
	@FXML TextField newUserStoryDescriptionTextField;
	@FXML DatePicker newUserStoryStartDateDatePicker;
	@FXML DatePicker newUserStoryEndDateDatePicker;
	@FXML Button changeUserStoryButton;
	@FXML Button addUserStoryButton;
	@FXML Button deleteUserStoryButton;
	@FXML Label warningLabel;
	@FXML Button button;
	
	private ObservableList<UserStoryModel> allUserStorys = FXCollections.observableArrayList(); 
	private UserStoryDAO userStoryDAO = new UserStoryDAO();
	private int selectedUserStoryID;
	
	/**
	 * Deze methode zorgt ervoor dat er in de combobox de naam van het project komt te staan
	 * in plaats van het volledige object.
	 * @author Jeroen Zandvliet
	 */

	public void fillProjectBox()
	{	
		ArrayList<ProjectModel> pList = new ProjectDAO().project_list();
		
		ObservableList<ProjectModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		
		Callback<ListView<ProjectModel>, ListCell<ProjectModel>> factory = lv -> new ListCell<ProjectModel>() {
		    @Override
		    protected void updateItem(ProjectModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getProjectName());
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
	 * Deze methode toont de popup waar de gebruiker een UserStory kan veranderen.
	 * @author Jeroen Zandvliet
	 */
	public void showPopUpChange() 
	{
		try{
			popUp.setVisible(true);
			
			UserStoryModel selectedItem = userStoryTableView.getSelectionModel().getSelectedItem();
			this.selectedUserStoryID = selectedItem.getUserStoryId();
//			changeProjectComboBox.setValue();
			changeUserStoryNameTextField.setText(selectedItem.getUserStoryName());
			changeUserStoryDescriptionTextField.setText(selectedItem.getUserStoryDescription());
//			changeUserStoryEndDateDatePicker.setValue(String.valueOf(selectedItem.getUserStoryStartDate()));
			changeUserStoryEndDateDatePicker.setValue(null);
			
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
	 * Deze methode roept de UserStoryDAO aan om een userStory in de database toe te voegen.
	 * @author Jeroen Zandvliet
	 * @throws SQLException
	 */
	public void addUserStory() throws SQLException
	{
		try
		{
		Date userStoryStartDate = Date.valueOf(newUserStoryStartDateDatePicker.getValue());
		Date userStoryEndDate = Date.valueOf(newUserStoryEndDateDatePicker.getValue());
//		new UserStoryDAO().addUserStoryToDatabase(addProjectComboBox.getSelectionModel().getSelectedItem().getProjectId(), newUserStoryNameTextField.getText(), newUserStoryDescriptionTextField.getText(), userStoryStartDate , userStoryEndDate);
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
	 * Deze methode roept de UserStoryDAO aan om een userStory in de database te modificeren.
	 * @author Jeroen Zandvliet
	 */
	public void modifyUserStory() 
	{		
		
			try{
				
				Date startDate = Date.valueOf(changeUserStoryStartDateDatePicker.getValue());
				Date endDate = Date.valueOf(changeUserStoryEndDateDatePicker.getValue());
				
//				userStoryDAO.modifyUserStory(this.selectedUserStoryID, changeUserStoryNameTextField.getText(), changeProjectComboBox.getSelectionModel().getSelectedItem().getProjectId(), changeUserStoryDescriptionTextField.getText(), startDate, endDate);
				
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
	 * Deze methode opent de popup die de gebruiker UserStorys laat toevoegen.
	 * @author Jeroen Zandvliet
	 */
	public void showPopUpAdd() 
	{
		popUpAdd.setVisible(true);
	}
	
	/**
	 * Deze methode verwijderd de userStory dat op dit moment geselecteerd is.
	 * @author Jeroen Zandvliet
	 */
	public void removeSelectedUserStory()
	{
		UserStoryModel selected_item = userStoryTableView.getSelectionModel().getSelectedItem();
		userStoryDAO.removeUserStory(selected_item.getUserStoryId());
		clearAllFields();
		refreshTable();
	}
	
	/**
	 * Deze methode zorgt ervoor dat de tabel ververst wordt.
	 * @author Jeroen Zandvliet
	 */
	public void refreshTable()
	{
		userStoryTableView.getItems().clear();
		userStoryID.setCellValueFactory(new PropertyValueFactory<UserStoryModel, Integer>("userStoryId"));
		userStoryName.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryName"));
		userStoryDescription.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryDescription"));
				
		userStoryStartDate.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryStartDate"));
		userStoryEndDate.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryEndDate"));
		projectName.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("projectName"));
		
		
		allUserStorys.addAll(userStoryDAO.userStory_list());
		userStoryTableView.setItems(allUserStorys);
		userStoryTableView.refresh();
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
		userStoryID.setCellValueFactory(new PropertyValueFactory<UserStoryModel, Integer>("userStoryId"));
		userStoryName.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryName"));
		userStoryDescription.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryDescription"));
		userStoryIsDeleted.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryIsDeleted"));
		
		userStoryStartDate.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryStartDate"));
		userStoryEndDate.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("userStoryEndDate"));
		projectName.setCellValueFactory(new PropertyValueFactory<UserStoryModel, String>("projectName"));
		
		allUserStorys.addAll(userStoryDAO.userStory_list());
		userStoryTableView.setItems(allUserStorys);
	}
	
	/**
	 * Deze methode maakt alle invulbare velden leeg.
	 * @author Jeroen Zandvliet
	 */
	public void clearAllFields()
	{
		changeUserStoryNameTextField.setText("");
		changeUserStoryDescriptionTextField.setText("");
		changeUserStoryStartDateDatePicker.setValue(null);
		changeUserStoryEndDateDatePicker.setValue(null);
		changeProjectComboBox.setValue(null);
		
		newUserStoryNameTextField.setText("");
		newUserStoryDescriptionTextField.setText("");
		newUserStoryStartDateDatePicker.setValue(null);
		newUserStoryEndDateDatePicker.setValue(null);
		addProjectComboBox.setValue(null);
	}
}

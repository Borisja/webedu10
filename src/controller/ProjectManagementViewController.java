package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.CustomerModel;
import model.EntryModel;
import model.ProjectModel;
/**
 * Deze klasse is gemaakt om projecten te kunnen beheren
 * @author rezanaser
 *
 */
public class ProjectManagementViewController implements Initializable{
	
	@FXML TableView<ProjectModel> tableViewProjects;
	@FXML TableColumn<ProjectModel, Integer> id;
	@FXML TableColumn<ProjectModel, String> projectName;
	@FXML TableColumn<ProjectModel, String> projectDes;
	@FXML TableColumn<ProjectModel, String> customerName;
	@FXML TableColumn<ProjectModel, String> projectIsdeleted;
	@FXML ComboBox<CustomerModel> customerCombo;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label lblProjectId;
	@FXML TextField projectChangeName;
	@FXML TextField projectDescription;
	@FXML TextField projectNewName;
	@FXML TextField projectNewDescription;
	@FXML Button changeProject;
	@FXML Label lblWarning;
	
	private ObservableList<ProjectModel> allProjects = FXCollections.observableArrayList(); 
	private ProjectDAO projectDAO = new ProjectDAO();
	private CustomerDAO customerDAO = new CustomerDAO();

	public ProjectManagementViewController(){
		System.out.println(this.getClass().toString()+": constructor");
	}
	
	/**
	 * Fill the project box, when item is selected load the rest.
	 */
	public void fillCustomerBox()
	{	
		ArrayList<CustomerModel> pList = new CustomerDAO().getCustomerList();
		
		ObservableList<CustomerModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		/**
		 * Change what you see in the combobox, to the description rather then the object address.
		 * Must study this to understand. works for now.
		 */
		Callback<ListView<CustomerModel>, ListCell<CustomerModel>> factory = lv -> new ListCell<CustomerModel>() {
		    @Override
		    protected void updateItem(CustomerModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getCustomer_name());
		    }

		};
		
		this.customerCombo.setItems(data);
		this.customerCombo.setCellFactory(factory);
		this.customerCombo.setButtonCell(factory.call(null));
		
	}
	/**
	 * Wanneer deze methode voegt een nieuwe project toe
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void addProject() 
	{
		// DIt is aangepast door Robert, als het neit werkt dan haal je het weg.
		// Ik kon het zelf niet testen want dan had ik pas een uur later kunnen pushen ofzo
		projectDAO.createAddProjectFunction();
//		projectDAO.addProjectToDatabase(projectNewName.getText(), projectNewDescription.getText(), customerCombo.getSelectionModel().getSelectedItem().getCustomer_id());
		projectDAO.addProject(projectNewName.getText(), projectNewDescription.getText(), customerCombo.getSelectionModel().getSelectedItem().getCustomer_id());
		refreshTable();
		projectNewName.setText(null);
		projectNewDescription.setText(null);
		closePopupAdd();
	}
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void modifyProject() 
	{
		int projectID = Integer.parseInt(lblProjectId.getText());
		try{
			projectDAO.modifyProject(projectID, projectChangeName.getText(), projectDescription.getText());
			refreshTable();
			closePopup();
		}
		
		catch(NullPointerException e)
		{
			lblWarning.setText("Selecteer een entry ");
		}
	}
	
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void showPopUpChange() 
	{
		try{
			ProjectModel selectedItem = tableViewProjects.getSelectionModel().getSelectedItem();
			lblProjectId.setText(String.valueOf(selectedItem.getProjectId()));
			projectChangeName.setText(selectedItem.getProjectName());
			projectDescription.setText(selectedItem.getProjectDescription());
			popUp.setVisible(true);
		}
		catch(NullPointerException e)
		{
			Alert showMessage = new Alert(AlertType.INFORMATION);
			showMessage.setTitle("Geen Entry geselecteerd");
			showMessage.setContentText("Selecteer een entry om verder te gaan ");
			showMessage.showAndWait();
		}
		
	}
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void showPopUpAdd() 
	{
		popUpAdd.setVisible(true);
		
	}
	/**
	 * Deze methode verwijdert het geselecteerde project
	 *@author rezanaser
	 */
	
	public void removeSelectedProject()
	{
		ProjectModel selected_item = tableViewProjects.getSelectionModel().getSelectedItem();
		projectDAO.removeProject(selected_item.getProjectId());
		refreshTable();
	}
	/**
	 * Deze methode word uitgevoerd wanneer er op refresh knop wordt gedrukt.
	 * Bijwerken lijst
	 * @author rezanaser
	 */
	
	public void refreshTable()
	{
		tableViewProjects.getItems().clear();
		id.setCellValueFactory(new PropertyValueFactory<ProjectModel, Integer>("projectId"));
		projectName.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectName"));
		projectDes.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectDescription"));
		projectIsdeleted.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectIsDeleted"));
		
		allProjects.addAll(projectDAO.project_list());
		tableViewProjects.setItems(allProjects);
		tableViewProjects.refresh();
	}
	
	/**
	 * Deze opent het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	/**
	 * Deze sluit het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	
	/**
	 * Deze sluit het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
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
		this.fillCustomerBox();//Vult customer box in met klantnamen
		id.setCellValueFactory(new PropertyValueFactory<ProjectModel, Integer>("projectId"));
		projectName.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectName"));
		projectDes.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectDescription"));
		projectIsdeleted.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectIsDeleted"));
		
		allProjects.addAll(projectDAO.project_list());
		tableViewProjects.setItems(allProjects);
		
		
		
	}
	


}

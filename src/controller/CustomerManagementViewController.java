package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.CustomerDAO;
import dao.ProjectDAO;
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

public class CustomerManagementViewController implements Initializable{
	@FXML TableView<CustomerModel> tableViewCustomers;
	@FXML TableColumn<CustomerModel, Integer> id;
	@FXML TableColumn<CustomerModel, String> customerName;
	@FXML TableColumn<CustomerModel, String> customerDes;
	@FXML TableColumn<CustomerModel, String> customerIsdeleted;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label lblCustomerId;
	@FXML TextField customerChangeName;
	@FXML TextField customerDescription;
	@FXML TextField customerNewName;
	@FXML TextField customerNewDescription;
	@FXML Button changeCustomer;
	private @FXML Button add;
	private @FXML Button remove;
	private @FXML Button modify;
	
	private ObservableList<CustomerModel> allCustomers = FXCollections.observableArrayList(); 
	private CustomerDAO customerDAO = new CustomerDAO();

	public void disableButtons()
	{
		this.add.setDisable(true);
		this.remove.setDisable(true);
		this.modify.setDisable(true);
	}
	/**
	 * Wanneer deze methode voegt een nieuwe klant toe
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void addCustomer() 
	{
		customerDAO.createAddCustomerFunction();
		customerDAO.addCustomer(customerNewName.getText(), customerNewDescription.getText());
		refreshTable();
		customerNewName.setText(null);
		customerNewDescription.setText(null);
		closePopupAdd();
	}
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void modifyCustomer() 
	{
		int customerID = Integer.parseInt(lblCustomerId.getText());
	    customerDAO.modifyCustomer(customerID, customerChangeName.getText(), customerDescription.getText());
		//projectDAO.modifyProject(projectID, projectChangeName.getText(), projectDescription.getText());
		refreshTable();
		closePopup();
	}
	
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void showPopUpChange() 
	{
		try{
			CustomerModel selectedItem = tableViewCustomers.getSelectionModel().getSelectedItem();
			lblCustomerId.setText(String.valueOf(selectedItem.getCustomer_id()));
			customerChangeName.setText(selectedItem.getCustomer_name());
			customerDescription.setText(selectedItem.getCustomer_description());
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
	
	public void removeSelectedCustomer()
	{
		CustomerModel selected_item = tableViewCustomers.getSelectionModel().getSelectedItem();
		customerDAO.removeCustomer(selected_item.getCustomer_id());
		refreshTable();
	}
	/**
	 * Deze methode word uitgevoerd wanneer er op refresh knop wordt gedrukt.
	 * Bijwerken lijst
	 * @author rezanaser
	 */
	
	public void refreshTable()
	{
		tableViewCustomers.getItems().clear();
		id.setCellValueFactory(new PropertyValueFactory<CustomerModel, Integer>("customer_id"));
		customerName.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_name"));
		customerDes.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_description"));
		customerIsdeleted.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_isdeleted"));
		
		allCustomers.addAll(customerDAO.getCustomerList());
		tableViewCustomers.setItems(allCustomers);
		tableViewCustomers.refresh();
	}
	
	/**
	 * Deze opent het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void showView()
	{
		refreshTable();
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
		id.setCellValueFactory(new PropertyValueFactory<CustomerModel, Integer>("customer_id"));
		customerName.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_name"));
		customerDes.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_description"));
		customerIsdeleted.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("customer_isdeleted"));
		
		allCustomers.addAll(customerDAO.getCustomerList());
		tableViewCustomers.setItems(allCustomers);
		
	}

}

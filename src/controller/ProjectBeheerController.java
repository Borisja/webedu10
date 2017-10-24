package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CustomerModel;

public class ProjectBeheerController {

	CustomerDAO customerDao = new CustomerDAO();
	
	@FXML Button editBtn;
	@FXML ComboBox<String> klantCB;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	@FXML Button newCustomer;
	@FXML Button newProject;
	@FXML Button newSprint;
	@FXML Button newUserStory;

	Stage customerStage = new Stage();
	@FXML TextField custName;
	@FXML TextArea custDescription;
	@FXML Button custAddBtn;
	@FXML Button custCancelBtn;


//	public ProjectBeheerController(Stage primaryStage) throws IOException{
//		Parent root = FXMLLoader.load(getClass().getResource("ProjectBeheer.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		System.out.println(this.getClass().toString()+": geinstancieerd");
//		primaryStage.show();
//	}
	public ProjectBeheerController() {
//		ObservableList<String> options =
//			    FXCollections.observableArrayList(
//			        "Option 1",
//			        "Option 2",
//			        "Option 3"
//			    );
//		klantCB = new ComboBox<String>();
//		klantCB.getItems().clear();
//		klantCB.setItems(options);
//		klantCB = new ComboBox();
//		klantCB.getItems().addAll(
//	            "jacob.smith@example.com",
//	            "isabella.johnson@example.com",
//	            "ethan.williams@example.com",
//	            "emma.jones@example.com",
//	            "michael.brown@example.com");
		klantCB = new ComboBox<String>();
//		List<String> list = new ArrayList<String>();
//        list.add("Item A");
//        list.add("Item B");
//        list.add("Item C");
//        klantCB.getItems().clear();
	}

	@FXML
	private void initialize() {
		klantCB.setValue("klant");
		update();
	}
	private void update(){
		ArrayList<CustomerModel> customers = customerDao.getCustomerList();
		ObservableSet<String> observableSet = FXCollections.observableSet();
		for(CustomerModel customer: customers) {
			observableSet.add(customer.getCustomer_name());
		}
		klantCB.setItems(FXCollections.observableArrayList(observableSet));
	}

	public void newCustomerPane(){
		System.out.println("new Customer");
		try {
			Parent root = null;
			root = FXMLLoader.load(getClass().getResource("ProjectBeheer_KlantEditor.fxml"));
			Scene scene = new Scene(root);
			customerStage.setScene(scene);
			System.out.println(this.getClass().toString()+": geinstancieerd");
			customerStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addCustomer(){
		System.out.println(this.getClass().toString()+": addCustomer name: "+custName.getText());
		System.out.println(this.getClass().toString()+": addCustomer Description: "+custDescription.getText());
		custName.setText(custName.getText()+" update!");
		customerDao.addCustomer(custName.getText(),custDescription.getText());
		this.update();
		hideCustomerPane();
	}
	public void hideCustomerPane(){
		System.out.println(this.getClass().toString()+": hideCustomerPane");
		customerStage.hide();
		System.out.println(this.getClass().toString()+": hideCustomerPane");
	}
	public void klant() {
		System.out.println("klant!");
		ProjectDAO projectDAO = new ProjectDAO();

	}
	public void test() {
		System.out.println("t werkt");
	}
	public void newCustomer(){
		
	}
}

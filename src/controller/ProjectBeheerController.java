package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dao.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.CustomerModel;

public class ProjectBeheerController {
	
	@FXML Button editBtn;
	@FXML ComboBox<String> klantCB;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	
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
		List<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
//        klantCB.getItems().clear();
	}
	@FXML
	private void initialize() {
		klantCB.setValue("klant");
		klantCB.setItems(obList);
	}
	public void test() {
		System.out.println("t werkt");
	}
	
	public void klant() {
		System.out.println("klant!");
		CustomerDAO customerDao = new CustomerDAO();
		ArrayList<CustomerModel> customers = customerDao.getCustomerList();
		//uhm
		for(CustomerModel customer: customers) {
			System.out.println(customer.getCustomer_name());
		}
	}
	public void newCustomer(){
		
	}
}

package controller;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.stage.Stage;
import model.CustomerModel;
import model.ProjectModel;

public class ProjectBeheerController {

	CustomerDAO customerDao = new CustomerDAO();
	ProjectDAO projectDAO = new ProjectDAO();
	
	@FXML Button editBtn;
	@FXML ComboBox<String> customerCB;
	@FXML ComboBox<String> projectCB;
	@FXML ComboBox<String> sprintCB;
	@FXML ComboBox<String> userStoryCB;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	@FXML Button newCustomer;
	@FXML Button newProject;
	@FXML Button newSprint;
	@FXML Button newUserStory;

	ArrayList<CustomerModel> selectedCustomers;
	ArrayList<ProjectModel> selectedProjects;


	Stage customerStage = new Stage();
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
//		customerCB = new ComboBox<String>();
//		customerCB.getItems().clear();
//		customerCB.setItems(options);
//		customerCB = new ComboBox();
//		customerCB.getItems().addAll(
//	            "jacob.smith@example.com",
//	            "isabella.johnson@example.com",
//	            "ethan.williams@example.com",
//	            "emma.jones@example.com",
//	            "michael.brown@example.com");
		customerCB = new ComboBox<String>();
//		List<String> list = new ArrayList<String>();
//        list.add("Item A");
//        list.add("Item B");
//        list.add("Item C");
//        customerCB.getItems().clear();
	}

	@FXML
	private void initialize() {
		customerCB.setValue("Klant");
		projectCB.setValue("Project");
		sprintCB.setValue("Sprint");
		userStoryCB.setValue("UserStory");
		updateCutomerCB();
		updateProjectCB();
	}
	public void updateCutomerCB(){
		this.selectedCustomers = customerDao.getCustomerList();
		ObservableSet<String> observableSet = FXCollections.observableSet();
		observableSet.add("Alle klanten");
		for(CustomerModel customer: this.selectedCustomers) {
			observableSet.add(customer.getCustomer_name());
		}
		customerCB.setItems(FXCollections.observableArrayList(observableSet));
	}
	public void updateProjectCB(){
		this.selectedProjects = projectDAO.project_list();
		ObservableSet<String> observableSet = FXCollections.observableSet();
		observableSet.add("Alle projecten");
		for(ProjectModel project: this.selectedProjects) {
			observableSet.add(project.getProjectName());
		}
		projectCB.setItems(FXCollections.observableArrayList(observableSet));
	}


	public void newCustomerPane(){
		System.out.println("new Customer");
		try {
			Parent root = null;
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("ProjectBeheer_KlantEditor.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			customerStage.setScene(scene);
			System.out.println(this.getClass().toString()+": geinstancieerd");
			customerStage.show();
			ProjectBeheerKlantController projectBeheerKlantController = loader.getController();
			projectBeheerKlantController.setProjectBeheerController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

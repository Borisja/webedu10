package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
import model.SprintModel;

public class ProjectBeheerController {

	final private String CUSTOMER_DEFAULT = "Alle Klanten";

	CustomerDAO customerDao = new CustomerDAO();
	ProjectDAO projectDAO = new ProjectDAO();
	
	@FXML Button editBtn;
	@FXML ComboBox<CustomerModel> customerCB;
	@FXML ComboBox<ProjectModel> projectCB;
	@FXML ComboBox<SprintModel> sprintCB;
	@FXML ComboBox<String> userStoryCB;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	@FXML Button newCustomer;
	@FXML Button newProject;
	@FXML Button newSprint;
	@FXML Button newUserStory;

	ArrayList<CustomerModel> selectedCustomers;
	ArrayList<ProjectModel> selectedProjects;


	Stage customerStage = new Stage();

	public ProjectBeheerController() {
	}

	@FXML
	private void initialize() {
//		customerCB.setValue("Klant");
//		projectCB.setValue("Project");
//		sprintCB.setValue("Sprint");
//		userStoryCB.setValue("UserStory");
		updateCutomerCB();
		updateProjectCB();
	}
	public void updateCutomerCB(){
		this.selectedCustomers = customerDao.getCustomerList();
//		ObservableSet<String> observableSet = FXCollections.observableSet();
//		observableSet.add("Alle klanten");
		for(CustomerModel customer: this.selectedCustomers) {
//			observableSet.add(customer.getCustomer_name());
			customerCB.getItems().add(customer);
		}
//		customerCB.setItems(FXCollections.observableArrayList(observableSet));
	}
	public void updateProjectCB(){
		this.selectedProjects = projectDAO.project_list();
//		ObservableSet<String> observableSet = FXCollections.observableSet();
//		observableSet.add("Alle klanten");
		for(ProjectModel project: this.selectedProjects) {
//			observableSet.add(customer.getCustomer_name());
			projectCB.getItems().add(project);
		}
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
//		if(customerCB.getValue().==CUSTOMER_DEFAULT){
//
//		}
//		for (CustomerModel customer: this.selectedCustomers) {
//			if(customer.getCustomer_name())
//		}
	}
	public void test() {
		System.out.println("t werkt");
	}
	public void newCustomer(){
		
	}
}

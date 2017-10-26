package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ProjectModel;
import model.SprintModel;

public class ProjectBeheerController {

	final private String CUSTOMER_DEFAULT = "Alle Klanten";
	final private int CUSTOMER_ID_DEFAULT = -1;
	final private String PROJECT_DEFAULT = "Alle Projecten";
	final private int PROJECT_ID_DEFAULT = -1;

	CustomerDAO customerDao = new CustomerDAO();
	ProjectDAO projectDAO = new ProjectDAO();
	
	@FXML Button editBtn;
	@FXML ComboBox<CustomerModel> customerCB;
	@FXML
	Tooltip customerTT;
	@FXML ComboBox<ProjectModel> projectCB;
	@FXML
	Tooltip projectTT;
	@FXML ComboBox<SprintModel> sprintCB;
	@FXML ComboBox<String> userStoryCB;
	@FXML
	Tooltip sprint_userstoryTT;
	ObservableList<String> obList = FXCollections.observableArrayList("klant1","klant2");
	@FXML Button newCustomer;
	@FXML Button newProject;
	@FXML Button newSprint;
	@FXML Button newUserStory;

	@FXML
	FlowPane infoPane;



	private CustomerModel selectedCustomer;
	private ProjectModel selectedProject;


	Stage customerStage = new Stage();

	public ProjectBeheerController() {
	}

	@FXML
	private void initialize() {
		updateCustomerCB();
		updateProjectCB();
	}
	public void updateCustomerCB(){
		customerCB.getItems().clear();

		//de default klant, voor als je alles wilt zien
		CustomerModel defaultCustomer = new CustomerModel();
		defaultCustomer.setCustomer_name(this.CUSTOMER_DEFAULT);
		defaultCustomer.setCustomer_id(this.CUSTOMER_ID_DEFAULT);
		customerCB.setValue(defaultCustomer);
		customerCB.getItems().add(defaultCustomer);

		ArrayList<CustomerModel> customers = customerDao.getCustomerList();
		for(CustomerModel customer: customers) {
			customerCB.getItems().add(customer);
		}
	}
	public void updateProjectCB(){
		projectCB.getItems().clear();

		ProjectModel projectModel = new ProjectModel();
		projectModel.setProjectName(this.PROJECT_DEFAULT);
		projectModel.setProjectId(this.PROJECT_ID_DEFAULT);
		projectCB.setValue(projectModel);
		projectCB.getItems().add(projectModel);

		ArrayList<ProjectModel> projects = new ArrayList<ProjectModel>();
		if(this.selectedCustomer!=null&&this.selectedCustomer.getCustomer_id()!=this.PROJECT_ID_DEFAULT){
			projects = projectDAO.project_list(this.selectedCustomer);
		}else{
			projects = projectDAO.project_list();
		}
		for(ProjectModel project: projects) {
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
			ProjectBeheerKlantEditorController projectBeheerKlantEditorController = loader.getController();
			projectBeheerKlantEditorController.setProjectBeheerController(this);
			projectBeheerKlantEditorController.setViewStage(customerStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void newProjectPane(){
		System.out.println("new Project");
		try {
			Parent root = null;
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("ProjectBeheer_ProjectEditor.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			customerStage.setScene(scene);
			System.out.println(this.getClass().toString()+": geinstancieerd");
			customerStage.show();
			ProjectBeheerProjectEditorController projectBeheerProjectEditorController = loader.getController();
			projectBeheerProjectEditorController.setProjectBeheerController(this);
			projectBeheerProjectEditorController.setViewStage(customerStage);
			if(this.selectedCustomer!=null&&this.selectedCustomer.getCustomer_id()!=this.CUSTOMER_ID_DEFAULT) {
				projectBeheerProjectEditorController.setCustomer(selectedCustomer);
			}
			if(this.selectedProject!=null&&this.selectedProject.getProjectId()==this.PROJECT_ID_DEFAULT){
				projectBeheerProjectEditorController.setValuesTo(selectedProject);
			}
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
		selectedCustomer=customerCB.getValue();
		updateProjectCB();
//		this.infoPane.getChildren().clear();
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("ProjectBeheer_Klant.fxml"));
			Parent root = loader.load();
			this.infoPane.getChildren().clear();
			this.infoPane.getChildren().add(root);
			ProjectBeheerKlantInfoController projectBeheerKlantInfoController = loader.getController();
			projectBeheerKlantInfoController.setValuesTo(selectedCustomer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void test() {
		System.out.println("t werkt");
	}
	public void newCustomer(){
		
	}
}

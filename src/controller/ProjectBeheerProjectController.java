package controller;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.CustomerModel;
import model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by Robert on 10/25/2017.
 */
public class ProjectBeheerProjectController {

    private CustomerModel customer;
    private ProjectBeheerController projectBeheerController;
    private CustomerDAO customerDAO = new CustomerDAO();
    private ProjectDAO projectDAO = new ProjectDAO();

    @FXML
    TextField projectName;
    @FXML
    TextArea projectDescription;
    @FXML
    ComboBox<CustomerModel> projectCustomers;
    @FXML
    Button projectAddBtn;
    @FXML Button projectCancelBtn;


    @FXML
    private void initialize(){
        projectCustomers.getItems().clear();

        ArrayList<CustomerModel> customers = customerDAO.getCustomerList();
        for(CustomerModel customer: customers) {
            projectCustomers.getItems().add(customer);
        }
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
        projectCustomers.setValue(customer);
    }
    public void setProjectBeheerController(ProjectBeheerController projectBeheerController) {
        this.projectBeheerController = projectBeheerController;
    }

    public void setValuesTo(ProjectModel projectModel){

    }

    public void addProject(){
        projectDAO.addProject(projectName.getText(),projectDescription.getText(),projectCustomers.getValue().getCustomer_id());
        this.projectBeheerController.updateProjectCB();
    }
}

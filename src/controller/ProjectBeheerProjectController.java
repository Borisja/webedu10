package controller;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by Robert on 10/25/2017.
 */
public class ProjectBeheerProjectController {

    private CustomerModel customer;
    private ProjectBeheerController projectBeheerController;
    private Stage viewStage;

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
    Label errorLbl;

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
    public void setViewStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void addProject(){
        if(projectCustomers.getValue()!=null){
            String name = projectName.getText().trim();
            String description = projectDescription.getText().trim();
            int custID = projectCustomers.getValue().getCustomer_id();
            if(name!=null&&!name.equals("")&&description!=null&&!description.equals("")){
                projectDAO.addProject(name,description,custID);
                this.projectBeheerController.updateProjectCB();
                this.viewStage.hide();
            }else{
                errorLbl.setOpacity(1);
            }
        }else{
            errorLbl.setOpacity(1);
        }
    }
    public void close(){
        this.viewStage.hide();
    }
}

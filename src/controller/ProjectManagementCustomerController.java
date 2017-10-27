package controller;

import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Robert on 10/24/2017.
 */
public class ProjectManagementCustomerController {
    CustomerDAO customerDao = new CustomerDAO();

    private ProjectManagementController projectBeheerController;

    private Stage viewStage = new Stage();

    @FXML
    TextField customerName;
    @FXML
    TextArea customerDescription;
    @FXML
    Button addCustomerButton;
    @FXML Button cancelCustomerButton; //?
    @FXML
    Label errorLabel;

    public void setProjectBeheerController(ProjectManagementController projectManagementController){
        this.projectBeheerController = projectManagementController;
    }
    public void setViewStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void addCustomer(){
        String name = customerName.getText().trim();
        String description = customerDescription.getText().trim();

        if(name!=null&&!name.equals("")&&description!=null&&!description.equals("")){
            customerDao.addCustomer(customerName.getText(),customerDescription.getText());
            projectBeheerController.updateCustomerCB();
            close();
        }else{
            errorLabel.setOpacity(1);
        }
    }
    public void close(){
        this.viewStage.hide();
    }
}

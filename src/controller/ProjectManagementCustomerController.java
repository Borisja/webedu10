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
    TextField custName;
    @FXML
    TextArea custDescription;
    @FXML
    Button custAddBtn;
    @FXML Button custCancelBtn;
    @FXML
    Label errorLbl;

    public void setProjectBeheerController(ProjectManagementController projectBeheerController){
        this.projectBeheerController = projectBeheerController;
    }
    public void setViewStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void addCustomer(){
        String name = custName.getText().trim();
        String description = custDescription.getText().trim();

        if(name!=null&&!name.equals("")&&description!=null&&!description.equals("")){
            customerDao.addCustomer(custName.getText(),custDescription.getText());
            projectBeheerController.updateCustomerCB();
            close();
        }else{
            errorLbl.setOpacity(1);
        }
    }
    public void close(){
        this.viewStage.hide();
    }
}

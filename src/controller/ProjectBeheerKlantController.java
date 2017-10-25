package controller;

import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Robert on 10/24/2017.
 */
public class ProjectBeheerKlantController {
    CustomerDAO customerDao = new CustomerDAO();

    ProjectBeheerController projectBeheerController;

    @FXML
    TextField custName;
    @FXML
    TextArea custDescription;
    @FXML
    Button custAddBtn;
    @FXML Button custCancelBtn;

    public void ProjectBeheerKlantControllerController (ProjectBeheerController projectBeheerController){
        this.projectBeheerController=projectBeheerController;
    }
    public void setProjectBeheerController(ProjectBeheerController projectBeheerController){
        this.projectBeheerController = projectBeheerController;
    }

    public void addCustomer(){
        System.out.println(this.getClass().toString()+": addCustomer name: "+custName.getText());
        System.out.println(this.getClass().toString()+": addCustomer Description: "+custDescription.getText());
        customerDao.addCustomer(custName.getText(),custDescription.getText());
        projectBeheerController.updateCutomerCB();
    }
}

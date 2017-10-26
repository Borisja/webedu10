package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.CustomerModel;
import model.ProjectModel;

/**
 * Created by Robert on 10/26/2017.
 */
public class ProjectBeheerKlantInfoController {
    @FXML
    TextField customerName;
    @FXML
    TextArea customerDescription;
    @FXML
    Button customerEdit;
    @FXML Button customerDelete;
    CustomerModel customerModel;

    @FXML public void initialize(){

    }

    public void setValuesTo(CustomerModel customerModel){
        this.customerModel=customerModel;
        if(customerModel.isSaved()){
            customerEdit.setOpacity(0);
        } else {
            customerEdit.setOpacity(1);
        }
        customerName.setText(customerModel.getCustomer_name());
        customerDescription.setText(customerModel.getCustomer_description());
    }

    public void editCustomerName(){
        customerModel.setCustomer_name(customerName.getText());
        customerModel.setSaved(false);
        customerEdit.setOpacity(1);
    }
    public void editCustomerDescription(){
        customerModel.setCustomer_description(customerDescription.getText());
        customerModel.setSaved(false);
        customerEdit.setOpacity(1);
    }
    public void edit(){
        customerModel.setSaved(true);
        customerEdit.setOpacity(0);
    }
    public void remove(){

    }
}

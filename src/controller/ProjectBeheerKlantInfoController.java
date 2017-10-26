package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.CustomerModel;
import model.ProjectModel;

/**
 * Created by Robert on 10/26/2017.
 */
public class ProjectBeheerKlantInfoController {
    @FXML
    Text customerName;
    @FXML
    Text customerDescription;
    public void setValuesTo(CustomerModel customerModel){
        customerName.setText(customerModel.getCustomer_name());
        customerDescription.setText(customerModel.getCustomer_description());
    }
}

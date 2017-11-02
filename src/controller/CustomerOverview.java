package controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CustomerModel;

public class CustomerOverview {
	public void customerViewShow(CustomerModel cm) {
		Stage customer_stage = new Stage();
		Label lbl_customer_id = new Label();
		Label lbl_customer_name = new Label();
		Label lbl_customer_description = new Label();
		VBox customer_vbox = new VBox();
		
		lbl_customer_id.setText(Integer.toString(cm.getCustomer_id()));
		lbl_customer_name.setText(cm.getCustomer_name());
		lbl_customer_description.setText(cm.getCustomer_description());
		
		customer_vbox.getChildren().addAll(lbl_customer_id, lbl_customer_name, lbl_customer_description);
		
		Scene customer_scene = new Scene(customer_vbox, 300, 200);
		customer_stage.setScene(customer_scene);
		customer_stage.show();
	}
}

package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmployeeModel;

public class EmployeeOverview {
	public void customer_view_show(EmployeeModel em) {
		Stage customer_stage = new Stage();
		Label lbl_customer_id = new Label();
		Label lbl_customer_name = new Label();
		Label lbl_customer_description = new Label();
		VBox customer_vbox = new VBox();
		
		lbl_customer_id.setText(Integer.toString(em.getEmployeeId()));
		lbl_customer_name.setText(em.getEmployeeVoornaam());
		lbl_customer_description.setText(em.getEmployeeEmail());
		
		customer_vbox.getChildren().addAll(lbl_customer_id, lbl_customer_name, lbl_customer_description);
		
		Scene customer_scene = new Scene(customer_vbox, 300, 200);
		customer_stage.setScene(customer_scene);
		customer_stage.show();
	}
}

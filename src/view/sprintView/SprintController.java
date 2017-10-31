package view.sprintView;

import java.net.URL;
import java.util.ResourceBundle;

import dao.SprintDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EmployeeModel;
import model.SprintModel;

public class SprintController  implements Initializable {
	
	@FXML Pane pane;
	@FXML TableView<SprintModel> sprintTabelView;
	@FXML TableColumn<SprintModel, Integer> sId;
	@FXML TableColumn<SprintModel, String> sprintName;
	@FXML TableColumn<SprintModel, String> sprintStartDate;
	@FXML TableColumn<SprintModel, String> sprintEndDate;
	private SprintDAO sprintDAO = new SprintDAO();
	private EmployeeModel emModel;
	private ObservableList<SprintModel>sprintData = FXCollections.observableArrayList();
	
	
	public void openSprintmenu(){
		pane.setVisible(true);
		
	}
	
	public void sluitSprintmenu(){
		pane.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//sId.setCellValueFactory(new PropertyValueFactory<SprintModel, Integer>("projectID"));	
	sprintName.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintName"));		
	sprintStartDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintStartDate"));		
	sprintEndDate.setCellValueFactory(new PropertyValueFactory<SprintModel, String>("sprintEndDate"));
	
	sprintData.addAll(sprintDAO.allSprints());
	sprintTabelView.setItems(sprintData);
	}

	public void setCurrentUser(EmployeeModel em) {
		this.emModel = em;
		
	}
	

}

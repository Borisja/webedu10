package view.projectView;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.EmployeeModel;
import model.ProjectModel;
import model.SprintModel;

public class ProjectController implements Initializable{
	@FXML Pane pane;
	@FXML TableView<ProjectModel> projectTabelView;
	@FXML TableColumn<ProjectModel, Integer> iId;
	@FXML TableColumn<ProjectModel, String> projectName;
	@FXML TableColumn<ProjectModel, String> projectDes;
	private ProjectDAO projectDao = new ProjectDAO();
	private int currentEmployee;
	private ObservableList<ProjectModel> projectsData = FXCollections.observableArrayList();
	public void openProjectScherm(){
		pane.setVisible(true);
		
	}
	
	public void sluitProjectScherm(){
		pane.setVisible(false);
	}

	public void fillTabelView()
	{
		projectsData.addAll(projectDao.project_list_employee(currentEmployee));
		projectTabelView.setItems(projectsData);
	}

	public void setCurrentUser(int  em) {
		this.currentEmployee = em;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iId.setCellValueFactory(new PropertyValueFactory<ProjectModel, Integer>("projectId"));
		projectName.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectName"));
		projectDes.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectDescription"));
		
	}

}

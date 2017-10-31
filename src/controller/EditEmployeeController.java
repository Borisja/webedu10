package controller;

import dao.AdministratorDAO;
import dao.EmployeeDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EmployeeModel;
import view.EditEmployeeView;

import java.io.IOException;

/**
 * Created by Robert on 10/31/2017.
 */
public class EditEmployeeController {
    private EmployeeDAO employeeDao = new EmployeeDAO();
    private AdministratorDAO administratorDao = new AdministratorDAO();
    private EditEmployeeView editEmployeeView;
//    Stage primaryStage = new Stage();
    private Pane editPane;

    public EditEmployeeController() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditEmployee.fxml"));
        editPane = loader.load();
        editPane.setVisible(false);
        Scene scene = new Scene(editPane);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        editEmployeeView=loader.getController();
        editEmployeeView.setEditEmployeeController(this);
        editEmployeeView.fillTable(employeeDao.activeAccountsList());
    }
    public void editEmployee(EmployeeModel employeeModel){
        employeeDao.editEmployee(employeeModel);
        editEmployeeView.fillTable(employeeDao.activeAccountsList());
    }
//    public void closeView(){
//        primaryStage.hide();
//    }
    public void showEditEmployee(){
        editEmployeeView.fillTable(employeeDao.activeAccountsList());
        editEmployeeView.getMainPane().setVisible(true);
    }
    public void closeEditEmployee(){
        editEmployeeView.getMainPane().setVisible(false);
    }

    public Pane getEditPane() {
        return editPane;
    }
    public EditEmployeeView getEditEmployeeView() {
        return editEmployeeView;
    }
}

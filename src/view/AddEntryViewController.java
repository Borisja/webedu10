package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.ProjectModel;

/**
 * Deze klasse is bestemd voor invullen entries.
 * @author rezanaser
 *
 */

public class AddEntryViewController {
	
	@FXML ComboBox projectCombo;
	@FXML ComboBox userStorysCombo;
	@FXML ComboBox sprintCombo;
	@FXML Button btnAddEntry;
	
	public AddEntryViewController()
	{
		//this.fillProjectsBox();
	}
	
	
	public void fillProjectsBox()
	{
//		projectCombo.setButtonCell(
//			    new ListCell<ProjectModel>() {
//			        @Override
//			        protected void updateItem(ProjectModel t, boolean bln) {
//			            super.updateItem(t, bln);
//			            if (bln) {
//			                setText("");
//			            } else {
//			                setText(t.getProjectName());
//			            }
//			        }
//			    });
//
//		projectCombo.setConverter(
//			    new StringConverter() {
//			        private Map<String, ProjectModel> map = new HashMap<>();
//
//			        @Override
//			        public String toString(ProjectModel t) {
//			            if (t != null) {
//			                String str = t.getProjectName();
//			                map.put(str, t);
//			                return str;
//			            } else {
//			                return "";
//			            }
//			        }
//
//			        @Override
//			        public Object fromString(String string) {
//			            if (validate && !map.containsKey(string)) {
//			            	projectCombo.setValue(null);
//			            	projectCombo.getEditor().clear();
//			                return null;
//			            }
//			            return map.get(string);
//			        }
//
//					@Override
//					public String toString(Object object) {
//						// TODO Auto-generated method stub
//						return null;
//					}
//			    });
//
//		projectCombo.setCellFactory(
//			    new Callback<ListView<Object>, ListCell<ProjectModel>>() {
//			        @Override
//			        public ListCell<Object> call(ListView<Object> p) {
//			            ListCell cell = new ListCell<Object>() {
//			                @Override
//			                protected void updateItem(Object item, boolean empty) {
//			                    super.updateItem(item, empty);
//			                    if (empty) {
//			                        setText("");
//			                    } else {
//			                        setText((item.));
//			                    }
//			                }
//			            };return cell;
//			        }
//			    });
		ArrayList<ProjectModel> pList = new ProjectDAO().project_list();
		ObservableList<String> data;
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e.getProjectName()));
		
		this.projectCombo.setItems(data);
		
		
		
	}
}

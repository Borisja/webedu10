package view;

import dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
	
	
	public void fillProjectsBox(ProjectDAO projectDAO)
	{
		
	}
}

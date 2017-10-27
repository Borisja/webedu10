package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.ProjectModel;
import model.SprintModel;
import model.UserStoryModel;
/**
 * Deze klasse is bedoel voor het toevoegen van de uren.
 * 
 * @author rezanaser
 *
 */
public class EntryController {
	@FXML Button add;
	@FXML Button close;
	@FXML Label projectLabel;
	@FXML Label userStoryLabel;
	@FXML Label sprintLabel;
	@FXML Pane pane;
	private ProjectModel projectModel;
	private SprintModel sprintModel;
	private UserStoryModel userStoryModel;
	
	/**
	 * Deze methode krijgt het geselecteerde projectmodel mee van AddEntryViewController
	 * @param project -> dit is het projectModel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChosenProject(ProjectModel project)
	{
		this.projectModel = project;
		this.projectLabel.setText(project.getProjectName());
	}
	
	/**
	 * Deze methode krijgt het geselecteerde userstoryModel mee van AddEntryViewController
	 * @param userstory  -> dit is het userstorymodel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChosenUserStory(UserStoryModel userstory)
	{
		this.userStoryModel = userstory;
		this.userStoryLabel.setText(userstory.getUserStoryName());
	}
	
	
	/**
	 * Deze methode krijgt het geselecteerde Sprintmodel mee van AddEntryViewController
	 * @param sprint  -> dit is het Sprintmodel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChosenSprint(SprintModel sprint)
	{
		this.sprintModel = sprint;
		this.sprintLabel.setText(sprint.getSprintName());
	}
	public void showView()
	{
		this.pane.setVisible(true);
	}
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	
	
}

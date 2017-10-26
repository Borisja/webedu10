package controller;

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
public class AddHoursController {
	@FXML Button toevoegen;
	@FXML Button sluit;
	@FXML Label project_naam_lbl;
	@FXML Label userstory_naam_lbl;
	@FXML Label sprint_naam_lbl;
	@FXML Pane pane;
	private ProjectModel projectModel;
	private SprintModel sprintModel;
	private UserStoryModel userStoryModel;
	
	/**
	 * Deze methode krijgt het geselecteerde projectmodel mee van AddEntryViewController
	 * @param project -> dit is het projectModel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChoosenProject(ProjectModel project)
	{
		this.projectModel = project;
		this.project_naam_lbl.setText(project.getProjectName());
	}
	
	/**
	 * Deze methode krijgt het geselecteerde userstoryModel mee van AddEntryViewController
	 * @param userstory  -> dit is het userstorymodel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChoosenUserstory(UserStoryModel userstory)
	{
		this.userStoryModel = userstory;
		this.userstory_naam_lbl.setText(userstory.getUserStoryName());
	}
	
	
	/**
	 * Deze methode krijgt het geselecteerde Sprintmodel mee van AddEntryViewController
	 * @param sprint  -> dit is het Sprintmodel meegekregen van AddEntryViewController
	 * @author rezanaser
	 */
	public void setChoosenSprint(SprintModel sprint)
	{
		this.sprintModel = sprint;
		this.sprint_naam_lbl.setText(sprint.getSprintName());
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

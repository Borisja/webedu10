package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EntryModel;
import model.ProjectModel;
import model.SprintModel;
import model.SprintModel;

/**
 * 
 * @author Fardin Samandar
 * @date 24-10-2017
 */
public class SprintDAO {
	ConnectDAO connect = new ConnectDAO();
	
	public ArrayList <SprintModel> toonUserSprint (int e_id){
		ArrayList<SprintModel> sprintLijst = new ArrayList <SprintModel>();
		String sprintQuery = "SELECT * FROM sprint_version, sprint "
				+ "WHERE sprint_version_sprint_fk = sprint_id";
		
		try {
			PreparedStatement sprintStatement = connect.connectToDB().prepareStatement(sprintQuery);
			ResultSet sprint_set = sprintStatement.executeQuery();
			while(sprint_set.next()){
				SprintModel model = new SprintModel();
				model.setSprintName(sprint_set.getString("sprint_version_name"));
				model.setSprintStartDate(sprint_set.getString("sprint_version_startdate"));
				model.setSprintEndDate(sprint_set.getString("sprint_version_enddate"));
				sprintLijst.add(model);
			}
			sprintStatement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprintLijst;
}


/**
 * Deze methode vult de combobox met de sprints van het gevraagde project
 * @author rezanaser
 * @return
 */
public ArrayList<SprintModel> sprintsProjects(int p_id){
	ArrayList<SprintModel> sprint_alist = new ArrayList<SprintModel>();
	String projects_sprints_sql = "SELECT *  FROM sprint_version where sprint_version_project_fk = ? ";
			//+ "AND entry_version_current = 'y' ";
	try {
		PreparedStatement sprints_statement = connect.connectToDB().prepareStatement(projects_sprints_sql);
		sprints_statement.setInt(1, p_id);
		ResultSet sprints_sets = sprints_statement.executeQuery();
		while(sprints_sets.next()) {
			SprintModel sprint = new SprintModel();
			sprint.setSprintId(sprints_sets.getInt("sprint_version_sprint_fk"));
			sprint.setSprintName(sprints_sets.getString("sprint_version_name"));
			sprint.setSprintStartDate(sprints_sets.getString("sprint_version_startdate"));
			sprint.setSprintEndDate(sprints_sets.getString("sprint_version_enddate"));
			sprint_alist.add(sprint);
		}
		sprints_statement.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return sprint_alist;
  }
}

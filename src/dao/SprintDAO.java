package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EntryModel;
import model.ProjectModel;
import model.SprintModel;

public class SprintDAO {
	private ConnectDAO connect = new ConnectDAO();
	/**
	 * Deze methode vult de combobox de sprints van het gevraagde project
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
				sprint.setSprintName(sprints_sets.getString("sprint_version_name"));
				sprint.setSprintStartDate(sprints_sets.getString("sprint_version_startdate"));
				sprint.setSprintEndDate(sprints_sets.getString("sprint_version_enddate"));
				sprint_alist.add(sprint);
			}
			sprints_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprint_alist;
	}

	
}

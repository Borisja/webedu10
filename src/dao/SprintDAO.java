package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String sprintQuery = "";
		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sprintLijst;
	}
	
}

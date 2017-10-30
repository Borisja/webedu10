package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SprintModel;
import model.UserStoryModel;

/**
 * Deze klasse is verantwoordelijk voor de userstories
 * @author rezanaser
 *
 */
public class UserStoryDAO {
	private ConnectDAO connect = new ConnectDAO(); // Connectie maken met de database
	
	/**
	 * Deze methode vult de combobox met de userstories van het gevraagde project
	 * @author rezanaser
	 * @return p_id >het project nummer
	 */
	public ArrayList<UserStoryModel> userstoriesProjects(int p_id){
		ArrayList<UserStoryModel> userstory_alist = new ArrayList<UserStoryModel>();
		String projects_userstories_sql = "SELECT *  FROM userstory_version where userstory_version_project_fk = ? ";
				//+ "AND entry_version_current = 'y' ";
		try {
			PreparedStatement userstories_statement = connect.connectToDB().prepareStatement(projects_userstories_sql);
			userstories_statement.setInt(1, p_id);
			ResultSet userstories_sets = userstories_statement.executeQuery();
			while(userstories_sets.next()) {
				UserStoryModel userstory = new UserStoryModel();
				userstory.setUserStoryId(userstories_sets.getInt("userstory_version_userstory_fk"));
				userstory.setUserStoryName(userstories_sets.getString("userstory_version_name"));
				userstory_alist.add(userstory);
			}
			userstories_statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userstory_alist;
	  }

}

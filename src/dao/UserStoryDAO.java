package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	

	/**
	 * 
	 * @author Fardin Samandar
	 * @date 24-10-2017
	 */

		
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

//--------------------------------------------------------------------------------------
		

			
			/**
			 * Deze methode vult de combobox met de userStorys van het gevraagde sprint
			 * @author rezanaser
			 * @return
			 */
			public ArrayList<UserStoryModel> userStorysSprints(int p_id){
				ArrayList<UserStoryModel> userStory_alist = new ArrayList<UserStoryModel>();
				String sprints_userStorys_sql = "SELECT *  FROM userStory_version where userStory_version_sprint_fk = ? ";
						//+ "AND entry_version_current = 'y' ";
				try {
					PreparedStatement userStorys_statement = connect.connectToDB().prepareStatement(sprints_userStorys_sql);
					userStorys_statement.setInt(1, p_id);
					ResultSet userStorys_sets = userStorys_statement.executeQuery();
					while(userStorys_sets.next()) {
						UserStoryModel userStory = new UserStoryModel();
						userStory.setUserStoryId(userStorys_sets.getInt("userStory_version_userStory_fk"));
						userStory.setUserStoryName(userStorys_sets.getString("userStory_version_name"));
						userStory_alist.add(userStory);
					}
					userStorys_statement.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return userStory_alist;
			  }


			/**
			 * Deze methode laat alleen de current version zien van een userStory.
			 * @author Jeroen Zandvliet
			 * 
			 * @return userStoryList
			 */
			public ArrayList<UserStoryModel> userStory_list(){
				ArrayList<UserStoryModel> userStoryList = new ArrayList<UserStoryModel>();
				String userStoryListSQL = "SELECT * FROM userStory_version "
						+ "INNER JOIN userStory ON (userStory_id = userStory_version_userStory_fk)"
						+ "AND userStory_version_current = true "
						+ "ORDER BY userStory_version.userStory_version_userStory_fk ASC";
				try {
					PreparedStatement userStory_statement = connect.connectToDB().prepareStatement(userStoryListSQL);
					ResultSet userStory_set = userStory_statement.executeQuery();
					while(userStory_set.next()) {
						UserStoryModel userStoryModelContainer = new UserStoryModel();
						userStoryModelContainer.setUserStoryId(userStory_set.getInt("userStory_id"));
						userStoryModelContainer.setUserStoryDescription(userStory_set.getString("userStory_version_description"));
						userStoryModelContainer.setUserStoryName(userStory_set.getString("userStory_version_name"));
						userStoryModelContainer.setDeleted(userStory_set.getBoolean("userStory_isdeleted"));
						userStoryModelContainer.setSprintFK(userStory_set.getInt("userStory_version_sprint_fk"));
						userStoryList.add(userStoryModelContainer);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				} 
				return userStoryList;
			}


			/**
			 * Deze methode geeft een lijst van userStorys die bij een sprint horen
			 * @param sprintModel
			 * @return
			 */
			
			public ArrayList<UserStoryModel> userStory_list(SprintModel sprintModel){
				ArrayList<UserStoryModel> userStory_list = new ArrayList<UserStoryModel>();
				String userStory_list_sql = "SELECT * FROM userStory_version "
						+ "INNER JOIN userStory ON (userStory.userStory_id = userStory_version.userStory_version_userStory_fk) "
						+ "WHERE userStory_version.userStory_version_sprint_fk="+sprintModel.getSprintId()
						+ " ORDER BY userStory_version.userStory_version_name ASC";
				try {
					PreparedStatement userStory_statement = connect.connectToDB().prepareStatement(userStory_list_sql);
					ResultSet userStory_set = userStory_statement.executeQuery();
					while(userStory_set.next()) {
						UserStoryModel userStoryModelContainer = new UserStoryModel();
						userStoryModelContainer.setUserStoryId(userStory_set.getInt("userStory_id"));
						userStoryModelContainer.setUserStoryDescription(userStory_set.getString("userStory_version_description"));
						userStoryModelContainer.setUserStoryName(userStory_set.getString("userStory_version_name"));
						userStoryModelContainer.setDeleted(userStory_set.getBoolean("userStory_isdeleted"));
						userStory_list.add(userStoryModelContainer);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return userStory_list;
			}
			
			/**
			 * Deze methode geeft alleen de userStorys terug van de meegegeven medewerker.
			 * @author Jeroen Zandvliet
			 * @param employeeID
			 * @return userStoryList
			 */
			
			public ArrayList<UserStoryModel> userStory_list_employee(int employeeID){
				ArrayList<UserStoryModel> userStory_list = new ArrayList<UserStoryModel>();
				String userStory_list_sql = "SELECT * FROM userStory_version";
				try {
					PreparedStatement userStory_statement = connect.connectToDB().prepareStatement(userStory_list_sql);
					userStory_statement.setInt(1, employeeID);
					ResultSet userStory_set = userStory_statement.executeQuery();
					
					while(userStory_set.next()) {
						UserStoryModel userStoryModelContainer = new UserStoryModel();
						
						userStoryModelContainer.setUserStoryId(userStory_set.getInt("userStory_version_sprint_fk"));
						userStoryModelContainer.setUserStoryDescription(userStory_set.getString("userStory_version_description"));
						userStoryModelContainer.setUserStoryName(userStory_set.getString("userStory_version_name"));
						userStory_list.add(userStoryModelContainer);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				}
				return userStory_list;
			}


			/**
			 * Deze methode geeft het gegenereerde ID van een userStory terug
			 * @author Jeroen Zandvliet
			 * @return generatedID
			 */
			public int createNewUserStory()
				{
					int generatedID = 0;
					PreparedStatement createUserStory;
					ResultSet userStoryID = null;
					String insertUserStoryStatement = "INSERT INTO userStory(userStory_isdeleted) VALUES(?)";
					
					try 
					{
						createUserStory = connect.connectToDB().prepareStatement(insertUserStoryStatement, Statement.RETURN_GENERATED_KEYS);
						
						createUserStory.setBoolean(1, false);
						createUserStory.executeUpdate();
			//			createUserStory.getGeneratedKeys();
						userStoryID = createUserStory.getGeneratedKeys();
						
						
						while(userStoryID.next())
						{
							generatedID = userStoryID.getInt(1);
						}
					
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
						return generatedID;
				}


			/**
			 * Deze methode voegt een gekozen userStory aan de database toe.
			 * @author Jeroen Zandvliet
			 * @param userStoryName
			 * @param sprintID
			 * @param userStoryDescription
			 * @param userStoryStartDate
			 * @param userStoryEndDate
			 */
			public void addUserStoryToDatabase(int sprintID, String userStoryName, String userStoryDescription)
			{
				PreparedStatement addUserStory;
				String insertStatement = "INSERT INTO userStory_version(userStory_version_userStory_fk, userStory_version_sprint_fk, userStory_version_name, userStory_version_description, userStory_version_startdate, userStory_version_enddate, userStory_version_current) " 
						+ "VALUES(?,?,?,?,?,?, true)";
				
				try 
				{
					addUserStory = connect.connectToDB().prepareStatement(insertStatement);
					
					addUserStory.setInt(1, createNewUserStory());
					addUserStory.setInt(2,  sprintID);
					addUserStory.setString(3, userStoryName);
					addUserStory.setString(4, userStoryDescription);
					
					addUserStory.executeQuery();
					addUserStory.close();			
					
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				} 
				
			}
			
			


			public ArrayList <UserStoryModel> toonUserUserStory (int e_id){
				ArrayList<UserStoryModel> userStoryList = new ArrayList <UserStoryModel>();
				String userStoryQuery = "SELECT * FROM userStory_version, userStory "
						+ "WHERE userStory_version_userStory_fk = userStory_id";
				
				try {
					PreparedStatement userStoryStatement = connect.connectToDB().prepareStatement(userStoryQuery);
					ResultSet userStory_set = userStoryStatement.executeQuery();
					while(userStory_set.next()){
						UserStoryModel model = new UserStoryModel();
						model.setUserStoryName(userStory_set.getString("userStory_version_name"));
						userStoryList.add(model);
					}
					userStoryStatement.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
				return userStoryList;
		}


		/**
			 * Deze methode past een eerder gemaakte userStory aan en zet de vorige versie op nonactief.
			 * @author Jeroen Zandvliet
			 * @param userStoryID
			 * @param userStoryName
			 * @param sprintID
			 * @param userStoryDescription
			 * @param userStoryStartDate
			 * @param userStoryEndDate
			 */
			
			public void modifyUserStory(int userStoryID, String userStoryName, int sprintID, String userStoryDescription)
			{
				String changePreviousVersion = "UPDATE userStory_version SET userStory_version_current = 'n' "
						+ "WHERE userStory_version_userStory_fk = ? AND userStory_version_current= true";
				String change_userStory = "INSERT INTO userStory_version(userStory_version_userStory_fk, userStory_version_name, userStory_version_sprint_fk, userStory_version_description, userStory_version_startdate, userStory_version_enddate, userStory_version_current)"
						+ "VALUES(?, ?, ?, ?, ?, ?, true)";
				
				
				try {
					PreparedStatement changeVersions= connect.connectToDB().prepareStatement(changePreviousVersion);
					changeVersions.setInt(1, userStoryID);
					changeVersions.executeUpdate();
					changeVersions.close();
					PreparedStatement changeUserStory = connect.connectToDB().prepareStatement(change_userStory);
					changeUserStory.setInt(1, userStoryID);
					changeUserStory.setString(2, userStoryName);
					changeUserStory.setInt(3, sprintID);
					changeUserStory.setString(4, userStoryDescription);
					changeUserStory.executeQuery();
					
					changeUserStory.close();
				} catch (Exception e) {
					e.getMessage();
				}
				
			}
			

			public void removeUserStory(int userStoryID) 
			{
				String deleteUserStory = "UPDATE userStory "
					+ "SET userStory_isdeleted = true "
					+ "WHERE userStory_id = ?";
				try 
				{
					PreparedStatement lockStatement = connect.connectToDB().prepareStatement(deleteUserStory);
					lockStatement.setInt(1, userStoryID);
					lockStatement.executeUpdate();
				} catch (Exception e) 
				{
				e.printStackTrace();
				}
			}
			


		}

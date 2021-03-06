package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import model.CustomerModel;
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
	/**
	 * Deze methode vult de combobox met de sprints van het gevraagde project
	 * @author rezanaser
	 * @return
	 */
	public ArrayList<SprintModel> allSprintsEmployee(int id){
		ArrayList<SprintModel> sprint_alist = new ArrayList<SprintModel>();
		String projects_sprints_sql = "select sprint_version_sprint_fk, sprint_version_name, sprint_version_description,sprint_version_startdate, sprint_version_enddate from sprint_version, project_employee where project_employee_employee_fk = ? AND  project_employee_project_fk = sprint_version_project_fk";
				
		try {
			PreparedStatement sprints_statement = connect.connectToDB().prepareStatement(projects_sprints_sql);
			sprints_statement.setInt(1, id);
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
	/**
	 * Deze methode vult de combobox met de sprints van het gevraagde project
	 * @author rezanaser
	 * @return
	 */
	public ArrayList<SprintModel> allSprints(){
		ArrayList<SprintModel> sprint_alist = new ArrayList<SprintModel>();
		String projects_sprints_sql = "SELECT *  FROM sprint_version";
				//+ "AND entry_version_current = 'y' ";
		try {
			PreparedStatement sprints_statement = connect.connectToDB().prepareStatement(projects_sprints_sql);
			
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
	
	/**
	 * Deze methode vult de combobox met de sprints van het gevraagde project
	 * @author rezanaser
	 * @return
	 */
	public ArrayList<SprintModel> sprintsProjects(int p_id){
		ArrayList<SprintModel> sprint_alist = new ArrayList<SprintModel>();
		String projects_sprints_sql = "SELECT *  FROM sprint_version sv INNER JOIN project_version pv " +
				"ON sv.sprint_version_project_fk=pv.project_version_project_fk INNER JOIN project p " +
				"ON p.project_id=pv.project_version_project_fk WHERE pv.project_version_project_fk= ?" +
				"AND sv.sprint_version_current=TRUE";
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


	/**
	 * Deze methode laat alleen de current version zien van een sprint.
	 * @author Jeroen Zandvliet
	 * 
	 * @return sprintList
	 */
	public ArrayList<SprintModel> sprint_list(){
		ArrayList<SprintModel> sprintList = new ArrayList<SprintModel>();
		String sprintListSQL = "SELECT * FROM sprint_version "
				+ "INNER JOIN sprint ON (sprint_id = sprint_version_sprint_fk) "
				+ "INNER JOIN project_version ON (sprint_version_project_fk = project_version_project_fk) "
				+ "AND sprint_version_current = true "
                + "AND project_version_current = true ";

		try {
			PreparedStatement sprint_statement = connect.connectToDB().prepareStatement(sprintListSQL);
			ResultSet sprint_set = sprint_statement.executeQuery();
			while(sprint_set.next()) {
				SprintModel sprintModelContainer = new SprintModel();
				sprintModelContainer.setSprintId(sprint_set.getInt("sprint_id"));
				sprintModelContainer.setSprintDescription(sprint_set.getString("sprint_version_description"));
				sprintModelContainer.setSprintName(sprint_set.getString("sprint_version_name"));
				sprintModelContainer.setSprintIsDeleted(sprint_set.getBoolean("sprint_isdeleted"));
				sprintModelContainer.setSprintStartDate(sprint_set.getString("sprint_version_startdate"));
				sprintModelContainer.setSprintEndDate(sprint_set.getString("sprint_version_enddate"));
				sprintModelContainer.setProjectName(sprint_set.getString("project_version_name"));
				sprintList.add(sprintModelContainer);
			}
			sprint_statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sprintList;
	}


	/**
	 * Deze methode geeft een lijst van sprints die bij een project horen
	 * @param projectModel
	 * @return
	 */
	
	public ArrayList<SprintModel> sprint_list(ProjectModel projectModel){
		ArrayList<SprintModel> sprint_list = new ArrayList<SprintModel>();
		String sprint_list_sql = "SELECT * FROM sprint_version "
				+ "INNER JOIN sprint ON (sprint.sprint_id = sprint_version.sprint_version_sprint_fk) "
				+ "WHERE sprint_version.sprint_version_project_fk="+projectModel.getProjectId()
				+ " ORDER BY sprint_version.sprint_version_name ASC";
		try {
			PreparedStatement sprint_statement = connect.connectToDB().prepareStatement(sprint_list_sql);
			ResultSet sprint_set = sprint_statement.executeQuery();
			while(sprint_set.next()) {
				SprintModel sprintModelContainer = new SprintModel();
				sprintModelContainer.setSprintId(sprint_set.getInt("sprint_id"));
				sprintModelContainer.setSprintDescription(sprint_set.getString("sprint_version_description"));
				sprintModelContainer.setSprintName(sprint_set.getString("sprint_version_name"));
				sprintModelContainer.setSprintIsDeleted(sprint_set.getBoolean("sprint_isdeleted"));
				sprint_list.add(sprintModelContainer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprint_list;
	}
	
	/**
	 * Deze methode geeft alleen de sprints terug van de meegegeven medewerker.
	 * @author Jeroen Zandvliet
	 * @param employeeID
	 * @return sprintList
	 */
	
	public ArrayList<SprintModel> sprint_list_employee(int employeeID){
		ArrayList<SprintModel> sprint_list = new ArrayList<SprintModel>();
		String sprint_list_sql = "SELECT * FROM sprint_version";
		try {
			PreparedStatement sprint_statement = connect.connectToDB().prepareStatement(sprint_list_sql);
			sprint_statement.setInt(1, employeeID);
			ResultSet sprint_set = sprint_statement.executeQuery();
			
			while(sprint_set.next()) {
				SprintModel sprintModelContainer = new SprintModel();
				
				sprintModelContainer.setSprintId(sprint_set.getInt("sprint_version_project_fk"));
				sprintModelContainer.setSprintDescription(sprint_set.getString("sprint_version_description"));
				sprintModelContainer.setSprintName(sprint_set.getString("sprint_version_name"));
				sprintModelContainer.setSprintStartDate(sprint_set.getString("sprint_version_startdate"));
				sprintModelContainer.setSprintEndDate(sprint_set.getString("sprint_version_enddate"));
				sprint_list.add(sprintModelContainer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprint_list;
	}


	/**
	 * Deze methode geeft het gegenereerde ID van een sprint terug
	 * @author Jeroen Zandvliet
	 * @return generatedID
	 */
	public int createNewSprint()
		{
			int generatedID = 0;
			PreparedStatement createSprint;
			ResultSet sprintID = null;
			String insertSprintStatement = "INSERT INTO sprint(sprint_isdeleted) VALUES(?)";
			
			try 
			{
				createSprint = connect.connectToDB().prepareStatement(insertSprintStatement, Statement.RETURN_GENERATED_KEYS);
				
				createSprint.setBoolean(1, false);
				createSprint.executeUpdate();
	//			createSprint.getGeneratedKeys();
				sprintID = createSprint.getGeneratedKeys();
				
				
				while(sprintID.next())
				{
					generatedID = sprintID.getInt(1);
				}
			
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
				return generatedID;
		}


	/**
	 * Deze methode voegt een gekozen sprint aan de database toe.
	 * @author Jeroen Zandvliet
	 * @param sprintName
	 * @param projectID
	 * @param sprintDescription
	 * @param sprintStartDate
	 * @param sprintEndDate
	 */
	public void addSprintToDatabase(int projectID, String sprintName, String sprintDescription, Date sprintStartDate, Date sprintEndDate)
	{
		PreparedStatement addSprint;
		String insertStatement = "INSERT INTO sprint_version(sprint_version_sprint_fk, sprint_version_project_fk, sprint_version_name, sprint_version_description, sprint_version_startdate, sprint_version_enddate, sprint_version_current) " 
				+ "VALUES(?,?,?,?,?,?, true)";
		
		try 
		{
			addSprint = connect.connectToDB().prepareStatement(insertStatement);
			
			addSprint.setInt(1, createNewSprint());
			addSprint.setInt(2,  projectID);
			addSprint.setString(3, sprintName);
			addSprint.setString(4, sprintDescription);
			addSprint.setDate(5, sprintStartDate);
			addSprint.setDate(6, sprintEndDate);
			
			addSprint.executeQuery();
			addSprint.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} 
		
	}
	
	/**
	 * Deze methode geeft sprints van de bijbehorende gebruiker
	 * Fardin Samandar
	 * @param e_id
	 * @return
	 */


	public ArrayList <SprintModel> toonUserSprint (int e_id){
		ArrayList<SprintModel> sprintList = new ArrayList <SprintModel>();
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
				sprintList.add(model);
			}
			sprintStatement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprintList;
}


/**
	 * Deze methode past een eerder gemaakte sprint aan en zet de vorige versie op nonactief.
	 * @author Jeroen Zandvliet
	 * @param sprintID
	 * @param sprintName
	 * @param projectID
	 * @param sprintDescription
	 * @param sprintStartDate
	 * @param sprintEndDate
	 */
	
	public void modifySprint(int sprintID, String sprintName, int projectID, String sprintDescription, Date sprintStartDate, Date sprintEndDate)
	{
		String changePreviousVersion = "UPDATE sprint_version SET sprint_version_current = false "
				+ "WHERE sprint_version_sprint_fk = ? AND sprint_version_current= true";
		String change_sprint = "INSERT INTO sprint_version(sprint_version_sprint_fk, sprint_version_name, sprint_version_project_fk, sprint_version_description, sprint_version_startdate, sprint_version_enddate, sprint_version_current)"
				+ "VALUES(?, ?, ?, ?, ?, ?, true)";
		
		
		try {
			PreparedStatement changeVersions= connect.connectToDB().prepareStatement(changePreviousVersion);
			changeVersions.setInt(1, sprintID);
			changeVersions.executeUpdate();
			changeVersions.close();
			PreparedStatement changeSprint = connect.connectToDB().prepareStatement(change_sprint);
			changeSprint.setInt(1, sprintID);
			changeSprint.setString(2, sprintName);
			changeSprint.setInt(3, projectID);
			changeSprint.setString(4, sprintDescription);
			changeSprint.setDate(5, sprintStartDate);
			changeSprint.setDate(6, sprintEndDate);
			changeSprint.executeQuery();
			
			changeSprint.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	

	public void removeSprint(int sprintID) 
	{
		String deleteSprint = "UPDATE sprint "
			+ "SET sprint_isdeleted = true "
			+ "WHERE sprint_id = ?";
		try 
		{
			PreparedStatement lockStatement = connect.connectToDB().prepareStatement(deleteSprint);
			lockStatement.setInt(1, sprintID);
			lockStatement.executeUpdate();
		} catch (Exception e) 
		{
		e.printStackTrace();
		}
	}
	


}

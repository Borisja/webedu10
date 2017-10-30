package dao;

import java.sql.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import model.CustomerModel;
import model.EmployeeModel;
import model.ProjectModel;

public class ProjectDAO {
	ConnectDAO connect = new ConnectDAO();
	ObservableList<ProjectModel> arrProjecten;

	/**
	 * Deze constructor maakt een stored procedure aan die een nieuw project kan toevoegen zonder onderbroken te worden
	 * door een andere gebruiker (atomicity).
	 *
	 * @author Robert den Blaauwen
	 * @date 25-10-2017
	 */
	public ProjectDAO(){
		String project_list_sql = "CREATE OR REPLACE FUNCTION add_project(name TEXT, description TEXT, customer INT4) " +
				"RETURNS void AS $$ " +
				"DECLARE pk INT; " +
				"BEGIN " +
				" INSERT INTO project(project_isdeleted) VALUES(false) " +
				"    RETURNING project_id INTO pk; " +
				"    INSERT INTO project_version(project_version_project_fk, project_version_name, project_version_description, project_version_customer_fk) " +
				"    VALUES(pk,name,description, customer); " +
				"END $$ LANGUAGE plpgsql;";
		try {
			PreparedStatement project_statement = connect.connectToDB().prepareStatement(project_list_sql);
			project_statement.executeUpdate();
			//System.out.println(this.getClass().toString()+": constructor: FUNCTION add_project(name, description, customer) has been created!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Met deze methode krijg je een lijst van huidige project versies terug
	 * @return Een lijst van laatste project versies
	 * @author rezanaser
	 */

	public ArrayList<ProjectModel> project_list(){
		ArrayList<ProjectModel> proj_list = new ArrayList<ProjectModel>();
		String project_list_sql = "SELECT * FROM project_version "
				+ "INNER JOIN project ON (project_id = project_version_project_fk)"
				+ "AND project_version_current = true "
				+ "ORDER BY project_version.project_version_name ASC";
		try {
			PreparedStatement project_statement = connect.connectToDB().prepareStatement(project_list_sql);
			ResultSet project_set = project_statement.executeQuery();
			while(project_set.next()) {
				ProjectModel pm_container = new ProjectModel();
				pm_container.setProjectId(project_set.getInt("project_id"));
				pm_container.setProjectDescription(project_set.getString("project_version_description"));
				pm_container.setProjectName(project_set.getString("project_version_name"));
				pm_container.setProjectIsDeleted(project_set.getBoolean("project_isdeleted"));
				proj_list.add(pm_container);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return proj_list;
	}
	public ArrayList<ProjectModel> project_list(CustomerModel customerModel){
		ArrayList<ProjectModel> proj_list = new ArrayList<ProjectModel>();
		String project_list_sql = "SELECT * FROM project_version "
				+ "INNER JOIN project ON (project.project_id = project_version.project_version_project_fk) "
				+ "WHERE project_version.project_version_customer_fk="+customerModel.getCustomer_id()
				+ " ORDER BY project_version.project_version_name ASC";
		try {
			PreparedStatement project_statement = connect.connectToDB().prepareStatement(project_list_sql);
			ResultSet project_set = project_statement.executeQuery();
			while(project_set.next()) {
				ProjectModel pm_container = new ProjectModel();
				pm_container.setProjectId(project_set.getInt("project_id"));
				pm_container.setProjectDescription(project_set.getString("project_version_description"));
				pm_container.setProjectName(project_set.getString("project_version_name"));
				pm_container.setProjectIsDeleted(project_set.getBoolean("project_isdeleted"));
				proj_list.add(pm_container);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proj_list;
	}

	public void addProject(String name, String description, int customerID) {
		String login_sql = "SELECT add_project('"+name+"','"+description+"','"+customerID+"')";
		PreparedStatement project_statement;

		try {
			project_statement = connect.connectToDB().prepareStatement(login_sql);
			project_statement.executeQuery();
			project_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deze methode wijzigt het geselcteerde project project
	 * @author rezanaser
	 */

	public void modifyProject(int pId, String name, String description) {
		String changePreviousVersion = "UPDATE project_version set project_version_current = 'n' "
				+ "WHERE project_version_project_fk = ?";
		String change_project = "INSERT INTO project_version(project_version_project_fk, project_version_name, project_version_description, project_version_current)"
				+ "VALUES(?, ?, ?, true)";
		try {
			PreparedStatement changeVersions= connect.connectToDB().prepareStatement(changePreviousVersion);
			changeVersions.setInt(1, pId);
			changeVersions.executeUpdate();
			changeVersions.close();
			PreparedStatement changeProject = connect.connectToDB().prepareStatement(change_project);
			changeProject.setInt(1, pId);
			changeProject.setString(2, name);
			changeProject.setString(3, description);
			changeProject.executeQuery();
			
			changeProject.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	/**
	 * Deze methode zet het project op inactive
	 * @param projectId > meegekregen van ProjectBeherenViewController
	 * @author rezanaser
	 */

	public void removeProject(int projectId) {
		String remove_project = "UPDATE project "
				+ "SET project_isdeleted = true "
				+ "WHERE project_id = ?";
		try {
			PreparedStatement lock_statement = connect.connectToDB().prepareStatement(remove_project);
			lock_statement.setInt(1, projectId);
			lock_statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * De volgende voegt een nieuwe project toe aan de project_version tabel
	 * @param pId Project ID
	 * @param projectName De naam van het project
	 * @param projectDes beschrijving van het project
	 * @author rezanaser
	 */
	public void addProjectToDatabase(String projectName, String projectDes, int customerId){
		PreparedStatement insertProject;
		String insertUser_sql = "insert into project_version (project_version_project_fk, project_version_name, project_version_description, project_version_current, project_version_customer_fk) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			insertProject = connect.connectToDB().prepareStatement(insertUser_sql);
			
			insertProject.setInt(1, createNewProject());
			insertProject.setString(2, projectName);
			insertProject.setString(3, projectDes);
			insertProject.setBoolean(4, true);
			insertProject.setInt(5, customerId);
			insertProject.executeQuery();

			insertProject.close();
			
		} catch (Exception e) {
			System.out.println(e);
			//e.printStackTrace();
		}
		
	}
	/**
	 * De volgende voegt een nieuwe project toe aan de project tabel
	 * @author rezanaser
	 */
	public int createNewProject() {
		int id = 0;
		PreparedStatement createProject;
		ResultSet projectId = null;
		String insertProject_sql = "insert into project (project_isdeleted) values (?)";
		
		try {
			
			createProject = connect.connectToDB().prepareStatement(insertProject_sql, Statement.RETURN_GENERATED_KEYS);
			
			createProject.setBoolean(1, false);
			createProject.executeUpdate();
			createProject.getGeneratedKeys();
			projectId = createProject.getGeneratedKeys();
			
			while (projectId.next()) {
				id = projectId.getInt(1);
			}
		} catch (Exception e) {
			
		}
		
		return id;
	}
	/**
	 * Deze methode geeft een overzicht van alle projecten die de employee aan deelneemt
	 * @param customerModel
	 * @return
	 * @author rezanaser
	 */
	public ArrayList<ProjectModel> project_list_employee(int employeeId){
		ArrayList<ProjectModel> proj_list = new ArrayList<ProjectModel>();
		String project_list_sql = "SELECT * FROM project_version";
		try {
			PreparedStatement project_statement = connect.connectToDB().prepareStatement(project_list_sql);
			project_statement.setInt(1, employeeId);
			ResultSet project_set = project_statement.executeQuery();
			
			while(project_set.next()) {
				ProjectModel pm_container = new ProjectModel();
				
				pm_container.setProjectId(project_set.getInt("project_version_project_fk"));
				pm_container.setProjectDescription(project_set.getString("project_version_description"));
				pm_container.setProjectName(project_set.getString("project_version_name"));
				proj_list.add(pm_container);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proj_list;
	}
	
}
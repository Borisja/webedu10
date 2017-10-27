package dao;

import java.sql.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import model.CustomerModel;
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
			System.out.println(this.getClass().toString()+": constructor: FUNCTION add_project(name, description, customer) has been created!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public ProjectDAO(){
//		System.out.println("test");
//		ConnectDAO connect = new ConnectDAO();
//		try {
//			Statement statement = connect.connectToDB().createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from project");
//			ResultSetMetaData metaData = resultSet.getMetaData();
//			System.out.println("test");
//			int numberOfColumns = metaData.getColumnCount();
//			
//			while (resultSet.next())
//			{ 
//				for(int kolom = 1; kolom <= numberOfColumns; kolom++)
//					System.out.println(resultSet.getString(kolom)+"\t\t\t");
//				System.out.println("\n=====");
//			}
//			
//			statement.close();
//			connect.connectToDB().close();
//			
//			
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	public ArrayList<ProjectModel> project_list(){
		ArrayList<ProjectModel> proj_list = new ArrayList<ProjectModel>();
		String project_list_sql = "SELECT * FROM project_version "
				+ "INNER JOIN project ON (project_id = project_version_project_fk) "
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
}
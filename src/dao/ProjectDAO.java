package dao;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.ProjectModel;

public class ProjectDAO {
	ConnectDAO connect = new ConnectDAO();
	ObservableList<ProjectModel> arrProjecten;
	
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
				+ "INNER JOIN project ON (project_id = project_version_project_fk)";
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
}
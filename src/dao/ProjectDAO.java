package dao;

import java.sql.*;


import javafx.collections.ObservableList;
import model.ProjectModel;

public class ProjectDAO {
	
	ObservableList<ProjectModel> arrProjecten;
	
	public ProjectDAO(){
		System.out.println("test");
		ConnectDAO connect = new ConnectDAO();
		try {
			Statement statement = connect.connectToDB().createStatement();
			ResultSet resultSet = statement.executeQuery("select * from project");
			ResultSetMetaData metaData = resultSet.getMetaData();
			System.out.println("test");
			int numberOfColumns = metaData.getColumnCount();
			
			while (resultSet.next())
			{ 
				for(int kolom = 1; kolom <= numberOfColumns; kolom++)
					System.out.println(resultSet.getString(kolom)+"\t\t\t");
				System.out.println("\n=====");
			}
			
			statement.close();
			connect.connectToDB().close();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
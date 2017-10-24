package dao;

import java.sql.*;
import java.util.ArrayList;


import javafx.collections.ObservableList;
import model.ProjectModel;

public class ProjectDAO {
	ConnectDAO connect = new ConnectDAO();
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

	/**
	 * Deze shit is WIP
	 *
	 * @author Robert den Blaauwen
	 * @date 24-10-2017
	 * @param customerName
	 * @return
	 */
//	public ArrayList<ProjectModel> getProjects(String customerName){
//		String login_sql = "SELECT * FROM project p INNER JOIN customer_version cv ON c.customer_id=cv.customer_version_customer_fk";
//		PreparedStatement customer_statement;
//
//		try {
//			customer_statement = connect.connectToDB().prepareStatement(login_sql);
////			customer_statement.setInt(1, c_id);
//			ResultSet customer_set = customer_statement.executeQuery();
//
//			ArrayList<CustomerModel> customers = new ArrayList<CustomerModel>();
//			while(customer_set.next()){
//				CustomerModel customer = new CustomerModel();
//				customer = new CustomerModel();
//				customer.setCustomer_id(customer_set.getInt("customer_id"));
//				customer.setCustomer_isdeleted(customer_set.getBoolean("customer_isdeleted"));
//				customer.setCustomer_name(customer_set.getString("customer_version_name"));
//				customer.setCustomer_description(customer_set.getString("customer_version_description"));
//				customers.add(customer);
//			}
//			customer_statement.close();
//			return customers;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
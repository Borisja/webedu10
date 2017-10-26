package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CustomerModel;

public class CustomerDAO {
	ConnectDAO connect = new ConnectDAO();

	/**
	 * Deze constructor maakt een stored procedure aan die een nieuwe klant kan toevoegen zonder onderbroken te worden
	 * door een andere gebruiker (atomicity).
	 *
	 * @author Robert den Blaauwen
	 * @date 25-10-2017
	 */
	public CustomerDAO(){
		String project_list_sql = "CREATE OR REPLACE FUNCTION add_customer(name TEXT, description TEXT) " +
				"RETURNS void AS $$ " +
				"DECLARE pk INT; " +
				"BEGIN " +
				" INSERT INTO customer(customer_isdeleted) VALUES(false) " +
				"    RETURNING customer_id INTO pk; " +
				"    INSERT INTO customer_version(customer_version_customer_fk, customer_version_name, customer_version_description) " +
				"    VALUES(pk,name,description); " +
				"END $$ LANGUAGE plpgsql; ";
		try {
			PreparedStatement project_statement = connect.connectToDB().prepareStatement(project_list_sql);
			project_statement.executeUpdate();
			System.out.println(this.getClass().toString()+": constructor: FUNCTION add_customer(name, description) has been created!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a customer model filled with information relating to given customer id
	 * If id does not match with any given customer you will get a empty customer
	 * @param c_id - to match with unique customer
	 * @return CustomerModel with specific customer information
	 */
	public CustomerModel customerInformation(int c_id) {
		String login_sql = "SELECT * FROM customer, customer_version WHERE customer_id = ?";
		PreparedStatement customer_statement;
		
		try {
			customer_statement = connect.connectToDB().prepareStatement(login_sql);
			customer_statement.setInt(1, c_id);
			ResultSet customer_set = customer_statement.executeQuery();
			
			CustomerModel customer = new CustomerModel();
			while(customer_set.next()){
				customer.setCustomer_id(customer_set.getInt("customer_id"));
				customer.setCustomer_isdeleted(customer_set.getBoolean("customer_isdeleted"));
				customer.setCustomer_name(customer_set.getString("customer_version_name"));
				customer.setCustomer_description(customer_set.getString("customer_version_description"));
				return customer;
			}
			customer_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author Robert
	 * @date 13-10-2017
	 * @return
	 */
	public ArrayList<CustomerModel> getCustomerList(){
//		String login_sql = "SELECT * FROM customer c INNER JOIN customer_version cv ON c.customer_id=cv.customer_version_customer_fk";
		String login_sql = "SELECT * FROM customer c INNER JOIN customer_version cv "
				+ "ON c.customer_id=cv.customer_version_customer_fk "
				+"ORDER BY cv.customer_version_name ASC";
		PreparedStatement customer_statement;
		
		try {
			customer_statement = connect.connectToDB().prepareStatement(login_sql);
//			customer_statement.setInt(1, c_id);
			ResultSet customer_set = customer_statement.executeQuery();
			
			ArrayList<CustomerModel> customers = new ArrayList<CustomerModel>();
			while(customer_set.next()){
				CustomerModel customer = new CustomerModel();
				customer = new CustomerModel();
				customer.setCustomer_id(customer_set.getInt("customer_id"));
				customer.setCustomer_isdeleted(customer_set.getBoolean("customer_isdeleted"));
				customer.setCustomer_name(customer_set.getString("customer_version_name"));
				customer.setCustomer_description(customer_set.getString("customer_version_description"));
				customers.add(customer);
			}
			customer_statement.close();
			return customers;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void addCustomer(String name, String description) {
		String login_sql = "SELECT add_customer('"+name+"','"+description+"')";
		PreparedStatement customer_statement;

		try {
			customer_statement = connect.connectToDB().prepareStatement(login_sql);
			ResultSet customer_set = customer_statement.executeQuery();
			customer_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.CustomerModel;

public class CustomerDAO {
	ConnectDAO connect = new ConnectDAO();
	
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
	
}

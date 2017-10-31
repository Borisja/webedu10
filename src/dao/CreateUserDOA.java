package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Boris Janjic
 *
 */

public class CreateUserDOA {
	
	private int createdUserId;
	
	ConnectDAO connect = new ConnectDAO();
	
	public CreateUserDOA(String firstName, String lastName, String role, String email, String password){
		PreparedStatement insertNewUser;
		String insertUser_sql = "insert into employee_version (employee_version_employee_fk, employee_version_firstname, employee_version_lastname, employee_version_role, "
				+ "employee_version_email, employee_version_password, employee_version_current) values (?, ?, ?, ?::enum_role, ?, ?,?)";
		try {
			insertNewUser = connect.connectToDB().prepareStatement(insertUser_sql);
			
			insertNewUser.setInt(1, createEmployee());
			insertNewUser.setString(2, firstName);
			insertNewUser.setString(3, lastName);
			insertNewUser.setString(4, role);
			insertNewUser.setString(5, email);
			insertNewUser.setString(6, password);
			insertNewUser.setBoolean(7,true);
			insertNewUser.executeUpdate();

			insertNewUser.close();
			
		} catch (Exception e) {
			System.out.println(e);
			//e.printStackTrace();
		}
		
	}
	
	public int createEmployee() {
		int id = 0;
		PreparedStatement insertEmployee;
		ResultSet employeeId = null;
		String insertEmployee_sql = "insert into employee (employee_isdeleted) values (?)";
		
		try {
			
			insertEmployee = connect.connectToDB().prepareStatement(insertEmployee_sql, Statement.RETURN_GENERATED_KEYS);
			
			insertEmployee.setBoolean(1, false);
			insertEmployee.executeUpdate();
			insertEmployee.getGeneratedKeys();
			employeeId = insertEmployee.getGeneratedKeys();
			
			while (employeeId.next()) {
				id = employeeId.getInt(1);
			}
		} catch (Exception e) {
			
		}
		
		return id;
	}
	
	public void setCreatedUserId(int id) {
		this.createdUserId = id;
	}
	
	public int getCreatedUserId() {
		return this.createdUserId;
	}
	
}

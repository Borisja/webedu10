package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.EntryModel;
import model.EmployeeModel;

/**
 * Employee DAO to be used to access database to get employee information
 */
public class EmployeeDAO {
	
	ConnectDAO connect = new ConnectDAO();
	
	/**
	 * Return a employee model filled with information relating to given employee id
	 * @param e_id - use the employee ID to link user to model
	 * @return employee model with given information
	 */
	public EmployeeModel employee_information(int e_id) {
		String employee_sql = "SELECT * FROM employee, employee_version WHERE employee_id = ?";
		PreparedStatement employee_statement;
		
		try {
			employee_statement = connect.connectToDB().prepareStatement(employee_sql);
			employee_statement.setInt(1, e_id);
			ResultSet user_set = employee_statement.executeQuery();
			
			while(user_set.next()){
				EmployeeModel user = new EmployeeModel(
						user_set.getInt("employee_id"),
						user_set.getBoolean("employee_isdeleted"),
						user_set.getString("employee_version_firstname"),
						user_set.getString("employee_version_lastname"),
						user_set.getString("employee_version_password"),
						user_set.getString("employee_version_email"),
						user_set.getString("employee_version_role"));
				return user;
			}
			employee_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return a list of entries belonging to user.
	 */
	public ArrayList<EntryModel> entry_list(int e_id){
		ArrayList<EntryModel> entry_alist = new ArrayList<EntryModel>();
		String employee_entry_sql = "SELECT entry_version_starttime, entry_version_endtime, entry_version_creationtime, entry_version_description "
				+ "FROM entry_version, entry "
				+ "WHERE entry_employee_fk = ?";
		try {
			PreparedStatement entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			entries_statement.setInt(1, e_id);
			
			ResultSet entry_set = entries_statement.executeQuery();
			while(entry_set.next()) {
				EntryModel dummy = new EntryModel();
				dummy.setEntryDescription(entry_set.getString("entry_version_description"));
				dummy.setEntryStartTime(entry_set.getString("entry_version_starttime"));
				dummy.setEntryEndTime(entry_set.getString("entry_version_endtime"));
				dummy.setEntryDate(entry_set.getString("entry_version_creationtime"));
				entry_alist.add(dummy);
			}
			entries_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entry_alist;
	}
	
}


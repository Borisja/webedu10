package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.EntryModel;
import model.EmployeeModel;
import model.Role;

/**
 * Employee DAO to be used to access database to get employee information
 */
public class EmployeeDAO {
	
	ConnectDAO connect = new ConnectDAO();
	
	public ArrayList<EmployeeModel> getAllEmployees(){
		//Empty list to return
		ArrayList<EmployeeModel> employee_alist = new ArrayList<EmployeeModel>();
		
		String employee_entry_sql = "SELECT * FROM employee, employee_version "
				+ "WHERE  employee_id = employee_version_employee_fk "
				+ "AND employee_version_current = true AND employee_isdeleted = FALSE ";
		try {
			PreparedStatement user_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			//entries_statement.setInt(1, e_id);
			
			ResultSet userSet = user_statement.executeQuery();
			while(userSet.next()) {
				EmployeeModel employee_container = new EmployeeModel(
				userSet.getInt("employee_id"), userSet.getBoolean("employee_isdeleted"), 
				userSet.getString("employee_version_firstname"), userSet.getString("employee_version_lastname"), 
				userSet.getString("employee_version_password"), userSet.getString("employee_version_email"), 
				userSet.getString("employee_version_role")
						
				);	
				employee_alist.add(employee_container);
			}
			user_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_alist;
	}
	
	public ArrayList<EmployeeModel> activeAccountsList(){
		//Empty list to return
		ArrayList<EmployeeModel> employee_alist = new ArrayList<EmployeeModel>();
		
		String employee_entry_sql = "SELECT * FROM employee, employee_version "
				+ "WHERE  employee_id = employee_version_employee_fk "
				+ "AND employee_isdeleted = false AND employee_version_current=TRUE";
		try {
			PreparedStatement user_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			//entries_statement.setInt(1, e_id);
			
			ResultSet userSet = user_statement.executeQuery();
			while(userSet.next()) {
				EmployeeModel employee_container = new EmployeeModel(
				userSet.getInt("employee_id"), userSet.getBoolean("employee_isdeleted"), 
				userSet.getString("employee_version_firstname"), userSet.getString("employee_version_lastname"), 
				userSet.getString("employee_version_password"), userSet.getString("employee_version_email"), 
				userSet.getString("employee_version_role")
						
				);	
				employee_alist.add(employee_container);
			}
			user_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_alist;
	}
	
	public ArrayList<EmployeeModel> lockedAccountsList(){
		//Empty list to return
		ArrayList<EmployeeModel> employee_alist = new ArrayList<EmployeeModel>();
		
		String employee_entry_sql = "SELECT * FROM employee, employee_version "
				+ "WHERE  employee_id = employee_version_employee_fk "
				+ "AND employee_isdeleted = true";
		try {
			PreparedStatement user_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			//entries_statement.setInt(1, e_id);
			
			ResultSet userSet = user_statement.executeQuery();
			while(userSet.next()) {
				EmployeeModel employee_container = new EmployeeModel(
				userSet.getInt("employee_id"), userSet.getBoolean("employee_isdeleted"), 
				userSet.getString("employee_version_firstname"), userSet.getString("employee_version_lastname"), 
				userSet.getString("employee_version_password"), userSet.getString("employee_version_email"), 
				userSet.getString("employee_version_role")
						
				);	
				employee_alist.add(employee_container);
			}
			user_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_alist;
	}
	
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

	public void editEmployee(EmployeeModel employeeModel){
		String oldVersionDisableSql = "UPDATE employee_version SET employee_version_current=false" +
				" WHERE employee_version_employee_fk = ? AND employee_version_current = true";
		String addNewVersionSql = "INSERT INTO employee_version(employee_version_employee_fk," +
				"employee_version_firstname,employee_version_lastname,employee_version_role,employee_version_email," +
				"employee_version_password,employee_version_current)" +
				"VALUES(?,?,?,?::enum_role,?,?,?)";
		try {
			PreparedStatement changeVersions= connect.connectToDB().prepareStatement(oldVersionDisableSql);
			changeVersions.setInt(1, employeeModel.getEmployeeId());
			changeVersions.executeUpdate();
			changeVersions.close();


			PreparedStatement addVersionStatement = connect.connectToDB().prepareStatement(addNewVersionSql);
			addVersionStatement.setInt(1,employeeModel.getEmployeeId());
			addVersionStatement.setString(2,employeeModel.getEmployeeFirstname());
			addVersionStatement.setString(3,employeeModel.getEmployeeLastName());
			addVersionStatement.setString(4,employeeModel.getEmployeeRole());
			addVersionStatement.setString(5,employeeModel.getEmployeeEmail());
			addVersionStatement.setString(6,employeeModel.getEmployeePassword());
			addVersionStatement.setBoolean(7,true);
			addVersionStatement.executeQuery();
			addVersionStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Return a list of entries belonging to user.
	 */
	public ArrayList<EntryModel> entry_list(int e_id){
		//Empty list to return
		ArrayList<EntryModel> entry_alist = new ArrayList<EntryModel>();
		
		String employee_entry_sql = "SELECT sprint_version_description, userstory_version_description, project_version_description, entry_id, entry_status, entry_version_description, entry_version_starttime, entry_version_endtime, entry_version_creationtime\r\n" + 
				"FROM entry_version \r\n" + 
				"INNER JOIN entry ON(entry_id = entry_version_entry_fk)\r\n" + 
				"INNER JOIN project_version ON(project_version_project_fk=entry_version_project_fk)\r\n" + 
				"INNER JOIN sprint_version ON(sprint_version_project_fk=project_version_project_fk)\r\n" + 
				"INNER JOIN userstory_version ON(userstory_version_project_fk=project_version_project_fk)\r\n" +
				"WHERE entry_employee_fk = ?";
		try {
			PreparedStatement entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			entries_statement.setInt(1, e_id);
			
			ResultSet entry_set = entries_statement.executeQuery();
			while(entry_set.next()) {
				EntryModel entry_container = new EntryModel();
				entry_container.setEntryProjectDescription(entry_set.getString("project_version_description"));
				entry_container.setEntrySprintDescription(entry_set.getString("sprint_version_description"));
				entry_container.setEntryUserStoryDescription(entry_set.getString("userstory_version_description"));
				entry_container.setEntryId(entry_set.getInt("entry_id"));
				entry_container.setEntryDescription(entry_set.getString("entry_version_description"));
				entry_container.setEntryStatus(entry_set.getString("entry_status"));
				entry_container.setEntryStartTime(entry_set.getString("entry_version_starttime"));
				entry_container.setEntryEndTime(entry_set.getString("entry_version_endtime"));
				entry_container.setEntryDate(entry_set.getString("entry_version_creationtime"));
				
				entry_alist.add(entry_container);
			}
			entries_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entry_alist;
	}
	
}


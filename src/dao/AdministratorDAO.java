package dao;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import model.EmployeeModel;
import model.EntryModel;

public class AdministratorDAO {

	ConnectDAO connect = new ConnectDAO();
	
	/**
	 * Method to assign a user to a empty user model to be used throughout the application.
	 * This is put inside the administrator class for convience.
	 * @param email - users email to verify login
	 * @param pw - users password to verify login
	 * @return a user model if login was successful
	 */
	public EmployeeModel loginAssignment(String email, String pw){

		String login_sql = "SELECT * "
				+ "FROM employee, employee_version "
				+ "WHERE employee_version_email = ? AND employee_version_password = ?"
				+ "AND employee_id = employee_version_employee_fk";
		PreparedStatement login_statement;
		
		try {
			login_statement = connect.connectToDB().prepareStatement(login_sql);
			login_statement.setString(1, email);
			login_statement.setString(2, pw);
			ResultSet user_set = login_statement.executeQuery();
			
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
			login_statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lock an employee
	 * @param - employee id to lock
	 */
	public void lockEmployee(int emp_id) {
		String lock_query = "UPDATE employee "
				+ "SET employee_isdeleted = true "
				+ "WHERE employee_id = ?";
		try {
			PreparedStatement lock_statement = connect.connectToDB().prepareStatement(lock_query);
			lock_statement.setInt(1, emp_id);
			lock_statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		/**
	 * Deze methode laat een lijst zien van entries die de status queued hebben.
	 * @author rezanaser
	 * @param e_id
	 * @return
	 */
	public ArrayList<EntryModel> entry_all_list(){
		ArrayList<EntryModel> entry_alist = new ArrayList<EntryModel>();
		String employee_entry_sql = "SELECT  * from entry ";
				//+ "AND entry_version_current = 'y' ";
		try {
			PreparedStatement entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			
			ResultSet entry_set = entries_statement.executeQuery();
			while(entry_set.next()) {
				EntryModel dummy = new EntryModel();
				dummy.setEntryId(entry_set.getInt("entry_id"));
				dummy.setEntryStatus(entry_set.getString("entry_status"));
				entry_alist.add(dummy);
			}
			entries_statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entry_alist;
	}
	/**
	 * Unlock an employee
	 * @param - employee id to lock
	 */
	public void unlockEmployee(int emp_id) {
		String lock_query = "UPDATE employee "
				+ "SET employee_isdeleted = false "
				+ "WHERE employee_id = ?";
		try {
			PreparedStatement lock_statement = connect.connectToDB().prepareStatement(lock_query);
			lock_statement.setInt(1, emp_id);
			lock_statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Deze methode laat een lijst zien van entries die de status queued hebben.
	 * @author rezanaser
	 * @param e_id
	 * @return
	 */
	public ArrayList<EntryModel> entry_queued_list(int e_id){
		ArrayList<EntryModel> entry_alist = new ArrayList<EntryModel>();
		String employee_entry_sql = "SELECT  entry_id, entry_version_starttime, entry_version_endtime, entry_version_creationtime, entry_version_description"
				+ " FROM entry_version, entry "
				+ "WHERE entry_version.entry_version_entry_fk = entry.entry_id AND entry.entry_status = 'queued' ";
				//+ "AND entry_version_current = 'y' ";
		try {
			PreparedStatement entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
			
			ResultSet entry_set = entries_statement.executeQuery();
			while(entry_set.next()) {
				EntryModel dummy = new EntryModel();
				dummy.setEntryId(entry_set.getInt("entry_id"));
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
	
	/**
	 * Deze methode krijgt de geselecteerde uur van de goedkeuren view en stuurt deze wijziging naar de database.
	 * @param id is de id van de entry.
	 * @author rezanaser
	 */
	public void approveHours(int id)
	{
		String employee_entry_sql = "UPDATE entry SET entry_status = 'approved' WHERE entry_id = ? ";
			try {
				PreparedStatement entries_statement;
				entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
				entries_statement.setInt(1, id);
				entries_statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	public void rejectHours(int id)
	{
		String employee_entry_sql = "UPDATE entry SET entry_status = 'rejected' WHERE entry_id = ? ";
			try {
				PreparedStatement entries_statement;
				entries_statement = connect.connectToDB().prepareStatement(employee_entry_sql);
				entries_statement.setInt(1, id);
				entries_statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	/**
	 * Deze methode voegt een nieuwe entry toe
	 */
	
	public void addEntry(int employeeId, int pId, int spId, String description){
		PreparedStatement insertProject;
		String insertUser_sql = "insert into entry_version (entry_version_entry_fk, entry_version_project_fk,entry_version_sprint_fk, entry_version_description, entry_version_current) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			insertProject = connect.connectToDB().prepareStatement(insertUser_sql);
			
			insertProject.setInt(1, createNewEntry(employeeId));
			insertProject.setInt(2, pId);
			
//			insertProject.setInt(3, 0);
			insertProject.setInt(3, spId);
//			insertProject.setTime(5, starttime); 
//			insertProject.setTime(6, endtime);
//			insertProject.setDate(7, date);
			insertProject.setString(4, description);
			insertProject.setBoolean(5, true);
			insertProject.executeQuery();

			insertProject.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * De volgende voegt een nieuwe entry toe aan de entry tabel
	 * @author rezanaser
	 */
	public int createNewEntry(int employeeId) {
		int id = 0;
		PreparedStatement createEntry;
		ResultSet projectId = null;
		String insertEntry_sql = "insert into entry(entry_employee_fk, entry_status, entry_islocked, entry_isdeleted) values (?,'queued',false,false)";
		
		try {
			
			createEntry = connect.connectToDB().prepareStatement(insertEntry_sql, Statement.RETURN_GENERATED_KEYS);
			
			createEntry.setInt(1, employeeId);
			createEntry.executeUpdate();
			createEntry.getGeneratedKeys();
			projectId = createEntry.getGeneratedKeys();
			
			while (projectId.next()) {
				id = projectId.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	/**
	 * Deze methode maakt een csv bestand van de database.
	 * @author rezanaser
	 */
	public void exportCsv()
	{
		String time = new String();
		String filename ="factuur.csv";
	    try {
	        FileWriter fw = new FileWriter(filename);
	        String query = "SELECT entry_version_date, entry_version_starttime, entry_version_endtime, entry_version_description, project_version_name, (entry_version_endtime - entry_version_starttime) AS Uren "
	        		+ "FROM entry_version, project_version "
	        		+ "WHERE entry_version_project_fk = project_version_project_fk "
	        		+"AND project_version_current = 'y'";
	        Statement stmt = connect.connectToDB().createStatement();
	        fw.append("Datum");
            fw.append(';');
            fw.append("BeginTijd");
            fw.append(';');
            fw.append("EindTijd");
            fw.append(';');
            fw.append("Project");
            fw.append(';');
            fw.append("Werkzaamheden");
            fw.append(';');
            fw.append("Uren");
            fw.append(';');
            fw.append("Algemeen");
            fw.append(';');
            fw.append("Praktijkbeoordelen");
            fw.append(';');
            fw.append("EduCourse");
            fw.append(';');
            fw.append("Overige");
            fw.append(';');
            fw.append("Actorius");
            fw.append(';');
            fw.append("Totaal");
            fw.append(';');
            fw.append("Check");
            fw.append('\n');
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            fw.append(rs.getString(1));
	            fw.append(';');
	            fw.append(rs.getString(2).substring(0, 5));
	            fw.append(';');
	            fw.append(rs.getString(3).substring(0, 5));
	            fw.append(';');
	            fw.append(rs.getString(5));
	            fw.append(';');
	            fw.append(rs.getString(4));
	            fw.append(';');
	            fw.append(rs.getString(6).substring(0, 5));
	            fw.append(';');
	            if(rs.getString(5).equals("Algemeen"))
	            	{
	            		fw.append(rs.getString(6).substring(0, 5));
	            	};
	            if(rs.getString(5).equals("Praktijkbeoordelen"))
	            	{
	            		fw.append(';');
	            		fw.append(rs.getString(6).substring(0, 5));
	            	}
	            if(rs.getString(5).equals("EduCourse"))
            	{
	            	fw.append(';');
	            	fw.append(';');
            		fw.append(rs.getString(6).substring(0, 5));
            	}
	            if(rs.getString(5).equals("Overige"))
            	{
	            	fw.append(';');
	            	fw.append(';');
	            	fw.append(';');
            		fw.append(rs.getString(6).substring(0, 5));
            	}
	            if(rs.getString(5).equals("Actorius"))
            	{
	            	fw.append(';');
	            	fw.append(';');
	            	fw.append(';');
	            	fw.append(';');
            		fw.append(rs.getString(6).substring(0, 5));
            	}
	            fw.append('\n');
	           }
	        fw.flush();
	        fw.close();
	        System.out.println("CSV File is created successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}

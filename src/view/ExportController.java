package view;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.AdministratorDAO;

public class ExportController {
	private AdministratorDAO adminDao = new AdministratorDAO();
	
	public void exportCSV()
	{
		adminDao.exportCsv();
	}

}

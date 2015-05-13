package QIAutomation.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import yodlee.gather.exceptions.GeneralException;


public class SEEDDBConnectionManager {
	
	public static Connection connectToRepalda(){
		Connection connection = null; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@192.168.202.235:1521:";
			String database = "repalda";
			connection = DriverManager.getConnection (url+database, "read", "read");
		} catch (ClassNotFoundException e) {
			// Could not find the database driver 
			throw new GeneralException("Could not find the database driver");
		}catch (SQLException e) {
			// Could not connect to the database
			throw new GeneralException("Could not connect to the database");
		}

		return connection;
	}
	
	public static Connection connectToSitep(String username,String password){

		Connection connection = null; 
		try {
			// Load the JDBC driver
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);

			// Create a connection to the database
			String serverName = "192.168.202.110";
			String portNumber = "1521";
			String instance = "sitep";
			String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + instance;

			connection = DriverManager.getConnection(url,username,password);
		}catch (ClassNotFoundException e) {
			// Could not find the database driver 
			throw new GeneralException("Could not find the database driver");
		}catch (SQLException e) {
			// Could not connect to the database
			throw new GeneralException("Could not connect to the database");
		}
		
		return connection;
	}
	
	
	public static Connection connectToOtherDB(String userName,String password){
		Connection connection = null; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:";
			String database = "XE";
			connection = DriverManager.getConnection (url+database,userName, password);
		} catch (ClassNotFoundException e) {
			// Could not find the database driver 
			throw new GeneralException("Could not find the database driver");
		}catch (SQLException e) {
			// Could not connect to the database
			throw new GeneralException("Could not connect to the database");
		}

		return connection;
	}

}

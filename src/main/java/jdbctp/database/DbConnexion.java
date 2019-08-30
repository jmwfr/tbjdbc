package jdbctp.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DbConnexion {
	
	private static Connection conn = null;
	
	public static Connection connect() {
		try {
			InputStream dbProperties = DbConnexion.class.getResourceAsStream("/database.properties");
			Properties prop = new Properties();
			prop.load(dbProperties);
			return DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
		} catch (IOException ex) {
			System.out.println("Error accessing DB properties file.");
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			return null;
		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
			return null;
		}
	}
	
	public static void disconnect() {
		try {
			if(conn != null && conn.isClosed() == false)
			{
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
		}
	}
	
	public static boolean testConnection() {
		try {
			connect();
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Connection success!");
			System.out.printf("You're using %s version %s\n", meta.getDatabaseProductName(), meta.getDatabaseProductVersion());
			return true;
		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
			return false;
		} finally {
			try {
				if(conn != null && conn.isClosed() == false) {
					disconnect();
				}
			} catch (SQLException ex) {
				System.out.printf("Error Code: %d\n", ex.getErrorCode());
				System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
				System.out.printf("SQL State: %s\n", ex.getSQLState());
			}
		}
	}
}

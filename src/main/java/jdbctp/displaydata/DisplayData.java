package jdbctp.displaydata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayData {
	
	public static void showEngineer(Statement stmt) throws SQLException {
		String sql = "SELECT numMatricule, nom, tel, datenaissance FROM ingenieur LIMIT 20";
		ResultSet rs = stmt.executeQuery(sql);
		
        String titleTemplate = "%-11s   %-80s   %-20s   %-20s\n";
        String separatorTemplate = "%-11s   %-80s   %-20s   %-20s\n";
        String rowTemplate = "%11d | %-80s | %-20s | %-20s\n";
        
        System.out.printf(titleTemplate, "[Matricule]", "[Nom]", "[Téléphone]", "[Date de Naissance]");
        System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20));
        
        while(rs.next()) {
        	System.out.printf(rowTemplate, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        	System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20));
        }
	}
	
	private static void showEngineerById(Statement stmt, int id) throws SQLException {
		String sql = String.format("SELECT numMatricule, nom, tel, datenaissance, ville FROM ingenieur WHERE numMatricule = %d", id);
		ResultSet rs = stmt.executeQuery(sql);
		
        String titleTemplate = "%-11s   %-80s   %-20s   %-20s   %-50s\n";
        String separatorTemplate = "%-11s   %-80s   %-20s   %-20s   %-50s\n";
        String rowTemplate = "%11d | %-80s | %-20s | %-20s | %-50s\n";
        
        System.out.printf(titleTemplate, "[Matricule]", "[Nom]", "[Téléphone]", "[Date de Naissance]", "[Ville]");
        System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20), createSeparator(50));
        
        while(rs.next()) {
        	System.out.printf(rowTemplate, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        	System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20), createSeparator(50));
        }
	}
	
	public static void showEngineerByCity(Connection conn, PreparedStatement stmt, String city) throws SQLException {
		String sql = "SELECT numMatricule, nom, tel, datenaissance FROM ingenieur WHERE ville = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, city);
		ResultSet rs = stmt.executeQuery();
		
        String titleTemplate = "%-11s   %-80s   %-20s   %-20s\n";
        String separatorTemplate = "%-11s   %-80s   %-20s   %-20s\n";
        String rowTemplate = "%11d | %-80s | %-20s | %-20s\n";
        
        System.out.printf(titleTemplate, "[Matricule]", "[Nom]", "[Téléphone]", "[Date de Naissance]");
        System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20));
        
        boolean hasEngineer = false;
        while(rs.next()) {
        	System.out.printf(rowTemplate, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        	System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(20), createSeparator(20));
        	hasEngineer = true;
        }
        
        if(!hasEngineer)
        	System.out.printf(" Pas d'ingénieur touvé à %s\n", city);
	}
	
	public static void showEngineerByCity(Connection conn, CallableStatement cstmt, String city) throws SQLException {
		String sql = "{CALL spGetIngenieursByCity (?)}";
		cstmt = conn.prepareCall(sql);
		cstmt.setString(1, city);
		ResultSet rs = cstmt.executeQuery();
		
		String titleTemplate = "%-11s   %-80s   %-80s   %-20s   %-150s   %-5s   %-50s   %-20s   %-6s   %-15s   %-25s\n";
        String separatorTemplate = "%-11s   %-80s   %-80s   %-20s   %-150s   %-5s   %-50s   %-20s   %-6s   %-15s   %-25s\n";
        String rowTemplate = "%11d | %-80s | %-80s | %-20s | %-150s | %-5s |  %-50s | %-20s | %-6s | %-15s | %-25s\n";
        
        System.out.printf(titleTemplate, "[Matricule]", "[Nom]", "[Prénom]", "[Date de Naissance]", "[Adresse]", "[CP]", "[Ville]", "[Téléphone]", "[Sexe]", "[Position]", "[Code Projet]" );
        System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(80), createSeparator(20), createSeparator(150), createSeparator(5), createSeparator(50), createSeparator(20), createSeparator(6), createSeparator(15), createSeparator(25));
        
        boolean hasEngineer = false;
        while(rs.next()) {
        	System.out.printf(rowTemplate, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
        	System.out.printf(separatorTemplate, createSeparator(11), createSeparator(80), createSeparator(80), createSeparator(20), createSeparator(150), createSeparator(5), createSeparator(50), createSeparator(20), createSeparator(6), createSeparator(15), createSeparator(25));
        	hasEngineer = true;
        }
        
        if(!hasEngineer)
        	System.out.printf(" Pas d'ingénieur touvé à %s\n", city);
	}
	
	private static String createSeparator(int size) {
        String sep = "";
        for (int i = 0; i < size; i++) {
            sep += "-";
        }
        return sep;
    }

	public static void updateEngineerCityWithRollback(Connection conn, PreparedStatement stmt, int numMatricule, String newCity) throws SQLException {
		conn.setAutoCommit(false);
		
		Statement tempStatement = conn.createStatement(); 
		showEngineerById(tempStatement, numMatricule);
		
		String sql = "UPDATE ingenieur SET ville = ? WHERE numMatricule = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, newCity);
		stmt.setInt(2, numMatricule);
		
		stmt.executeUpdate();
		showEngineerById(tempStatement, numMatricule);
		conn.rollback();
		
		showEngineerById(tempStatement, numMatricule);
		tempStatement.close();
	}
	
	public static void moveEngineerCity(Connection conn, PreparedStatement stmt, String oldCity, String newCity) throws SQLException {
		conn.setAutoCommit(false);
		
		String sql = "UPDATE ingenieur SET ville = ? WHERE ville = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, newCity);
		stmt.setString(2, oldCity);
		
		int result = stmt.executeUpdate();
		if(result > 0)
		{
			conn.commit();
			System.out.printf("%d Engineers living in %s have been moved to %s\n", result, oldCity, newCity);
		}
		else
		{
			conn.rollback();
			System.out.printf("No Engineer living in %s\n", oldCity);
		}
		
	}
}

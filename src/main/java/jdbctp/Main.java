package jdbctp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jdbctp.database.*;
import jdbctp.engineer.Ingenieur;
import jdbctp.engineer.IngenieurManager;
import jdbctp.displaydata.*;

public class Main {

	public static void main(String[] args) {
		//DbData.CreateEngineers(400);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DbConnexion.connect();
			
			/* TP 2-1 */
			//stmt = conn.createStatement();
			//DisplayData.showEngineer(stmt);
			
			/* TP 2-2 */
			//DisplayData.showEngineerByCity(conn, pstmt, "Paris");
			//DisplayData.showEngineerByCity(conn, pstmt, "Lyon");
			
			/* TP 2-3 */
			//DisplayData.updateEngineerCityWithRollback(conn, pstmt, 407, "Grenoble");
			
			/* TP 2-4/5 */
			//DisplayData.moveEngineerCity(conn, pstmt, "Nantes", "Colombes"); //23
			//DisplayData.moveEngineerCity(conn, pstmt, "Toulouse", "Pau"); //28
			//DisplayData.moveEngineerCity(conn, pstmt, "Lille", "Mongeron"); //27
			
			/* TP 2-6 */
			DisplayData.showEngineerByCity(conn, cstmt, "Lyon");
			
			//Scanner reader = new Scanner(System.in);  // Reading from System.in
			//System.out.print("Enter origin city: ");
			//String oldCity = reader.next();
			//System.out.print("Enter target city: ");
			//String newCity = reader.next();
			//reader.close();
			//DisplayData.moveEngineerCity(conn, pstmt, oldCity, newCity);
			
			/* TP 2-6 */
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (stmt != null && stmt.isClosed() == false) {
                    stmt.close();
                }
                if (pstmt != null && pstmt.isClosed() == false) {
                    pstmt.close();
                }
                if (cstmt != null && cstmt.isClosed() == false) {
                    cstmt.close();
                }
                if (conn != null && conn.isClosed() == false) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
		
		//TP 3
		IngenieurManager im = new IngenieurManager();
		
		//Find example
		Ingenieur inge423 = im.findById(423);
		System.out.println("Ingénieur 423: " + inge423.toString());
		
		//Update example
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("nom", "King");
		data.put("datenaissance", "1969-10-09");
		
		im.update(423, data);
		inge423 = im.findById(423);
		System.out.println("Ingénieur 423: " + inge423.toString());
		
		//Insertion example
		/*List<Object> insertData= new ArrayList<Object>();
		insertData.add("Lenchanteur");
		insertData.add("Merlin");
		insertData.add("892-01-09");
		insertData.add("1, route de Brocéliande");
		insertData.add("22000");
		insertData.add("Redon");
		insertData.add("+33 (0)245789562");
		insertData.add("m");
		insertData.add("célibataire");
		insertData.add(1.22547);
		
		im.insert(insertData);*/
		
		im.delete(801);
	}
	
	
}

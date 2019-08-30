package jdbctp.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class DbData {
	
	public static void CreateEngineers(int number) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DbConnexion.connect();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			for (int i = 0; i < number; i++) {
				String sql = String.format(Locale.ROOT, "INSERT INTO ingenieur VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %f)", getRandomLastName(), getRandomFirstName(), getRandomBirthDate(), getRandomAddress(), getRandomZipCode(), getRandomCity(), getRandomPhoneNumber(), getRandomSex(), getRandomSituation(), getRandomDouble());
				stmt.addBatch(sql);
			}

			int[] result = stmt.executeBatch();
			
			int count = 1;
			boolean hasError = false;
			for (int r : result) {
				if (r == 0) {
					System.out.printf("Request number %d has failed.\n", count);
					hasError = true;
				}
				count++;
			}
			if (!hasError) {
				conn.commit();
				System.out.println("Data created successfully!");
			}

		} catch (SQLException ex) {
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Error Code: " + ex.getErrorCode());
			System.out.println("Error: " + ex.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e) {
				System.out.println("SQL State: " + e.getSQLState());
				System.out.println("Error Code: " + e.getErrorCode());
				System.out.println("Error: " + e.getMessage());
			}
		} 
		catch (Exception ex) {
			System.out.println("Unhandled error creating data");
			System.out.println("Error: " + ex.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e) {
				System.out.println("SQL State: " + e.getSQLState());
				System.out.println("Error Code: " + e.getErrorCode());
				System.out.println("Error: " + e.getMessage());
			}
		} 
		finally {
			try {
				if (stmt != null && stmt.isClosed() == false) {
					stmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("Error while closing statement or connection");
				System.out.println("SQL State: " + ex.getSQLState());
				System.out.println("Error Code: " + ex.getErrorCode());
				System.out.println("Error: " + ex.getMessage());
			}
		}
	}

	public static String getRandomBirthDate() {
		Random r = new Random();
		
		int month = r.nextInt(12 - 1) + 1;
		int[] evenMonth = {4,6,9,11};
		int[] oddMonth = {1,3,5,7,8,10,12};
		int max = 28;
		if(Arrays.binarySearch(evenMonth, month) > -1)
		{
			max = 30;
		}
		else if(Arrays.binarySearch(oddMonth, month) > -1)
		{
			max = 31;
		}
		int day = r.nextInt(max - 1) + 1;
		int year = r.nextInt(2001 - 1930) + 1930;
		String birthDate = year + "-" + month + "-" + day;
		return birthDate;
	}
	
	public static String getRandomAddress() {
		String[] streetType = {"rue", "avenue", "boulevard", "route", "place"};
		String[] streetName = {"du Général Leclerc", "de la République", "de la Poste", "de la Mairie", "Charles de Gaulle", "de Paris", "de la Fontaine"};
		
		Random r = new Random();
		int num = r.nextInt(1000-1) + 1;
		int iType = r.nextInt(streetType.length-1) + 1;
		int iName = r.nextInt(streetName.length-1) + 1;
		String type = streetType[iType];
		String name = streetName[iName];
		
		String address = num + ", " + type + " " + name;
		return address;
	}
	
	public static String getRandomLastName() {
		Random r = new Random();
		String[] nameList = {"Dupond", "Cho", "Ramful", "Dos Santos", "Michel", "Jones", "Doe", "Dupont", "Le Blanc", "Martin", "Richard", "King", "Lovecraft"};
		int iName = r.nextInt(nameList.length-1) + 1;
		String name = nameList[iName];
		return name;
	}
	
	public static String getRandomFirstName() {
		Random r = new Random();
		String[] nameList = {"Michel", "Michelle", "Charles", "Consuela", "Pedro", "Bridget", "John", "Marie", "Pierre", "Elise", "Jean", "Stephen", "Howard"};
		int iName = r.nextInt(nameList.length-1) + 1;
		String name = nameList[iName];
		return name;
	}
	
	public static String getRandomZipCode() {
		Random r = new Random();
		String zipCode = "";
		int count = 5;
		for(int d = 0; d < count; d++) {
			zipCode += r.nextInt(9-0); 
		}
		return zipCode;
	}
	
	public static String getRandomCity() {
		Random r = new Random();
		String[] cityList = {"Paris", "Bordeaux", "Lyon", "Marseille", "Lille", "Orléans", "Rouen", "Strasbourg", "Rennes", "Nantes", "Brest", "Toulouse", "Dijon", "Le Mans"};
		int iCity = r.nextInt(cityList.length-1) + 1;
		String city = cityList[iCity];
		return city;
	}
	
	public static String getRandomPhoneNumber() {
		Random r = new Random();
		String phoneNumber = "+33 (0)";
		int count = 9;
		for(int d = 0; d < count; d++) {
			phoneNumber += r.nextInt(9-0); 
		}
		return phoneNumber;
	}
	
	public static String getRandomSex() {
		Random r = new Random();
		String[] sexList = {"m", "f"};
		int iSex = r.nextInt(sexList.length-1) + 1;
		String sex = sexList[iSex];
		return sex;
	}
	
	public static String getRandomSituation() {
		Random r = new Random();
		String[] situationList = {"marié", "célibataire", "pacsé"};
		int iSituation = r.nextInt(situationList.length-1) + 1;
		String situation = situationList[iSituation];
		return situation;
	}
	
	public static Double getRandomDouble() {
		Random r = new Random();
		return r.nextDouble();
	}
}

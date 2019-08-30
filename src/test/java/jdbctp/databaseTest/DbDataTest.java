package jdbctp.databaseTest;

import static org.junit.Assert.*;

import org.junit.Test;

import jdbctp.database.DbData;

public class DbDataTest {

	@Test
	public void testGetRandomBirthDate() {
		String birthDate = DbData.getRandomBirthDate();
		System.out.println("BirthDate: " + birthDate);
		assertNotEquals(null, birthDate);
	}

	@Test
	public void testGetRandomAddress() {
		String address = DbData.getRandomAddress();
		System.out.println("Address: " + address);
		assertNotEquals(null, address);
	}
	
	@Test
	public void testGetRandomLastName() {
		String lastName = DbData.getRandomLastName();
		System.out.println("LastName: " + lastName);
		assertNotEquals(null, lastName);
	}
	
	@Test
	public void testGetRandomFirstName() {
		String firstName = DbData.getRandomFirstName();
		System.out.println("FirstName: " + firstName);
		assertNotEquals(null, firstName);
	}
	
	@Test
	public void testGetRandomZipCode() {
		String zipCode = DbData.getRandomZipCode();
		System.out.println("ZipCode: " + zipCode);
		assertNotEquals("", zipCode);
	}
	
	@Test
	public void testGetRandomCity() {
		String city = DbData.getRandomCity();
		System.out.println("City: " + city);
		assertNotEquals(null, city);
	}
	
	@Test
	public void testGetRandomPhoneNumber() {
		String phoneNumber = DbData.getRandomPhoneNumber();
		System.out.println("PhoneNumber: " + phoneNumber);
		assertNotEquals(null, phoneNumber);
	}
	
	@Test
	public void testGetRandomSex() {
		String sex = DbData.getRandomSex();
		System.out.println("Sex: " + sex);
		assertNotEquals(null, sex);
	}
	
	@Test
	public void testGetRandomSituation() {
		String situation = DbData.getRandomSituation();
		System.out.println("Situation: " + situation);
		assertNotEquals(null, situation);
	}
	
	@Test
	public void testGetRandomDouble() {
		Double dbl = DbData.getRandomDouble();
		System.out.println("CodeProjet: " + dbl);
		assertNotEquals(null, dbl);
	}
}

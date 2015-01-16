package org.zz.LR4_JDBC_PostgreSQL;

import java.sql.SQLException;

import junit.framework.TestCase;

public class JDBCTests extends TestCase {
	
	DB db = new DB();

	protected void setUp() throws Exception {
		super.setUp();
		
	    db.dropTable();
	    db.createTable();
	    db.addTestUsers();
	    db.printTable();
	  
	}
	
	public void testUsersDidntLogInALongTimeAgo() throws SQLException{
		assertEquals("Ermiliy	2014-06-07 01:01:56"
				+ "\nVelimir	2014-07-07 06:17:49"
				+ "\nYaromir	2014-10-19 22:08:16"
				+ "\nIosif	2014-12-02 11:12:11\n"
				, db.topTen("asc"));
	}
	
	public void testNumbOfUsers() throws SQLException {		
		assertEquals(4, db.UsersQuantity());
	}
	
	public void testLoginMsg() throws SQLException	{
		assertEquals("Ermiliy вошел в систему. Сообщение: Здравствуйте", db.login("fluttershy77", "43g453f34"));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
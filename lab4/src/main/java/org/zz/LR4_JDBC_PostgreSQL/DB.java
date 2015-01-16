package org.zz.LR4_JDBC_PostgreSQL;

import java.util.Calendar;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DB {
	private Connection db_connection = null;
    private Statement sql_stmt = null;
    private ResultSet rs = null;
    
    // Установление соединения
    public static  Connection getDBConnection() throws SQLException {
        Connection db_connection = null;
        String db_url = "jdbc:postgresql://localhost:5432/postgres";
        String db_username = "postgres";
        String db_password = "password1";
        
        db_connection = DriverManager.getConnection(db_url, db_username, db_password);
        return db_connection;
    }
    
    // Добавление строк в таблицу
    public void add(String username, String surname, String message, String login, String password, String lastEntry) throws SQLException
    {
    	
    	try {
    		String insertTableSQL = "INSERT INTO userlist"
	                + "(username, surname, message, login, password, last_entry) " + "VALUES"
	                + "('" + username + "', '" + surname + "', '" + message + "', '" + login + "', '" 
	                + password + "', '" + lastEntry + "')";
	    	db_connection = DB.getDBConnection();
	    	sql_stmt = db_connection.createStatement();
	        sql_stmt.executeUpdate(insertTableSQL);
    	}
    	
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
		}
    	
    	finally {
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	}
    }
    
    // Заполнение таблицы тестовыми данными 
    public void addTestUsers() throws SQLException
    {
    	this.add("Yaromir", "Savateev", "Добрый день", "yarik", "tr67gjh87", "2014-10-19 22:08:16");
    	this.add("Ermiliy", "Gavriilov", "Здравствуйте", "fluttershy77", "43g453f34", "2014-06-07 01:01:56");
    	this.add("Velimir", "Denisiev", "Привет", "velden", "asdqwqY9", "2014-07-07 06:17:49");
    	this.add("Iosif", "Stanimirov", "Добрый вечер!", "yosya54", "qwertsdfsdf", "2014-12-02 11:12:11");
    }
    
    // Вывод всей таблицы
    public void printTable() throws SQLException{
        
    	try {
	    	String selectTableSQL = "SELECT username, surname, message, login, password, last_entry from userlist";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        rs = sql_stmt.executeQuery(selectTableSQL);
	
	        System.out.println("username     surname     message     login     password     last_entry");
	        while (rs.next()) {
	            String username = rs.getString("username");
	            String surname = rs.getString("surname");
	            String message = rs.getString("message");
	            String login = rs.getString("login");
	            String password = rs.getString("password");
	            String last_entry = rs.getString("last_entry");
	
	            System.out.println(username+"\t"+surname+"\t"+message+"\t"+login+"\t"+password+"\t"+last_entry);
            }
	        System.out.print("\n");
    	}
    	
    	finally {
    		if (rs != null) {
	            rs.close();
    	    }
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	}
    }
    
    // 1 Вывод имен пользователей и их текстовых сообщений
    public void printUsersMessages() throws SQLException{
        
    	try {	
	    	String selectTableSQL = "SELECT message,login from userlist";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        rs = sql_stmt.executeQuery(selectTableSQL);
	
	        System.out.println("login\tmessage");
	        while (rs.next()) {
	            String message = rs.getString("message");
	            String login = rs.getString("login");
	            System.out.println(login+"\t"+message);
	        }
        }
    	
    	finally {
    		if (rs != null) {
	            rs.close();
    	    }
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	}  
    }
    
    // 2 Подсчет количества пользователей
    public int UsersQuantity() throws SQLException{
        
    	try {
	    	String selectTableSQL = "SELECT COUNT(*) as total FROM  userlist";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        rs = sql_stmt.executeQuery(selectTableSQL);
	        int quantity=0;
	        while(rs.next()){
	        	quantity = rs.getInt("total");
	        }
	        return quantity;
	    }
        
        finally {
    		if (rs != null) {
	            rs.close();
    	    }		
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	}  
    }
    
    // 3 Вывод топ-10 недавно входивших в систему пользователей
    // 4 Вывод топ-10 давно не входивших в систему пользователей
    public String topTen(String order) throws SQLException{
        
    	try {
	    	String selectTableSQL = "SELECT * FROM userlist  ORDER BY  last_entry "+order+" LIMIT 10 ";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        rs = sql_stmt.executeQuery(selectTableSQL);
	        String username="";
	        String last_entry="";
	        String res="";
	        while(rs.next()){
	            username = rs.getString("username");
	            last_entry = rs.getString("last_entry");
	            res += username+"\t"+last_entry+"\n";
	        }
	        return res;
    	}
    	
    	finally {
    		if (rs != null) {
	            rs.close();
    	    }
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	} 
    }
    
    // 5 Авторизация пользователя по логину и паролю (в БД должно сохраниться новое время входа)
    public String login(String login, String password) throws SQLException{
        
    	try {
	    	String selectTableSQL = "SELECT * FROM userlist WHERE login='"+login+"' AND password='"+password+"'";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        rs = sql_stmt.executeQuery(selectTableSQL);
	        String username="";
	        String message="";
	        if(rs.next()){
	            username = rs.getString("username");
	            message = rs.getString("message");
	
	            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	            Calendar cal = Calendar.getInstance();
	            selectTableSQL="UPDATE userlist SET last_entry = '" + dateFormat.format(cal.getTime())
	            		+ "' WHERE login = '"+login+"'";
	            sql_stmt.execute(selectTableSQL);
	            Main.logged_in_user=login;
	            return username+" вошел в систему. Сообщение: "+message;
	        }
	        return "";
    	}
    	
    	finally {
    		if (rs != null) {
	            rs.close();
    	    }
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	} 
    }
    
    // 7 Ввод сообщения авторизованного пользователя
    public void newMessage(String login, String message) throws SQLException{
    	
    	try {
	        String updateTableSQL = "UPDATE userlist SET message = '" + message + "' WHERE login = '" + login + "'";
	        db_connection = DB.getDBConnection();
	        sql_stmt = db_connection.createStatement();
	        sql_stmt.execute(updateTableSQL);
    	}
    	
    	finally {
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	} 
    }
    
    // Создание таблицы
    public void createTable() throws SQLException {
    	try {
	    	String createTableSQL = "CREATE TABLE userlist (\n"
	    			+ "	username varchar(80),\n	surname varchar(80),\n"
	    			+ "	message varchar(80),\n"
	    			+ "	login varchar(80),\n"
	    			+ "	password varchar(80),\n"
	    			+ "	last_entry timestamp\n"
	    			+ ");";
	    	db_connection = DB.getDBConnection();
	    	sql_stmt = db_connection.createStatement();
	    	sql_stmt.execute(createTableSQL);
    	}
    	
    	finally {
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	} 
    }
    
    // Удаление таблицы
    public void dropTable() throws SQLException {
    	try {
	    	String dropTableSQL = "DROP TABLE userlist;";
	    	db_connection = DB.getDBConnection();
	    	sql_stmt = db_connection.createStatement();
	    	sql_stmt.execute(dropTableSQL);
    	}
    	
    	finally {
    		if (sql_stmt != null) {
    			sql_stmt.close();
			}
			if (db_connection != null) {
				db_connection.close();
			}
    	} 
    }
}
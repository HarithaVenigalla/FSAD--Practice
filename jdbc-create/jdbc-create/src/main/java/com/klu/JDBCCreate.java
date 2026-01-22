package com.klu;
import java.sql.*;
import java.sql.DriverManager;

public class JDBCCreate {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/fsad52";
		String usr="root";
		String pwd="123456";
		try {
			Connection con=DriverManager.getConnection(url,usr,pwd);
			System.out.println("Connection established");
			String query = "CREATE TABLE IF NOT EXISTS Student (" +
		               "id INT PRIMARY KEY AUTO_INCREMENT, " +
		               "name VARCHAR(20)" +
		               ");";
			Statement st = con.createStatement();
			st.execute(query);
			System.out.println("Table Created");
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

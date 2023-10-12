package com.amdocs.plant.dao;
import java.sql.*;

public class PlantJdbcConnection {
	public Connection connect() {
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}

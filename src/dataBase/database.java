package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class database {	
	//this loads the mysql driver to our project 
	public Connection createConnection () 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//here we are loading the diver
			//below we have established a connection between the java program and mysql sever
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mwanzobarakadb","root","davi");
			System.out.print("Database connection sucessful");
			return con;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

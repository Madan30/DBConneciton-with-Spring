package com.NepalCode.DBConwithSpring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HostelDB {
	private String driver;
	private String url;
	private String username;
	private String password;

	private Connection con;

	// to inject literals dependency we need setter and constructor injection
	public void setDriver(String driver) {
		System.out.println("Setting driver");
		this.driver = driver;
	}

	public void setUrl(String url) {
		System.out.println("setting url");
		this.url = url;
	}

	public void setUsername(String username) {
		System.out.println("setting username");
		this.username = username;
	}

	public void setPassword(String password) {
		System.out.println("setting password");
		this.password = password;
	}
	
	// postConstruct: annotation in spring 
	@PostConstruct
	public void init() throws SQLException {
		System.out.println("Inside the custom init method");
		dbConnection();
		
	}

	public void dbConnection() throws SQLException {
		// load the driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get the connection
		con = DriverManager.getConnection(url, username, password);
	}

	public void showInfo() throws SQLException {
		dbConnection();
		// create statement for quer
		Statement st = con.createStatement();

		// set the resultset
		ResultSet rs = st.executeQuery("select * from studentdatabase.studentinfo");

		while (rs.next()) {
			int studentId = rs.getInt(1);
			String studentName = rs.getString(2);
			String phoneNUm = rs.getString(3);
			double fee = rs.getDouble(4);

			System.out.println(studentId + " " + studentName + " " + phoneNUm + " " + fee);
		}
		
		closeConnection();
	}
	
	public void deleteRecord(int studentId) throws SQLException {
		dbConnection();
		// create statement
		Statement st = con.createStatement();
		
		// execute query
		st.executeUpdate("delete from studentdatabase.studentinfo");
		
		System.out.println("Deleting record");
		closeConnection();
	}
	
	public void closeConnection() throws SQLException {
		// close the connection
		con.close();
	}
	
	// preDestory annotation
	@PreDestroy
	public void destroy() throws SQLException {
		// clean the job
		closeConnection();
		System.out.println("Destroying the spring object");
	}

}

package com.lancaster.dsl.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StorageContext {
	private static Connection con;	
	private Statement stmt;
	private final static String URL = "jdbc:mysql://127.0.0.1/slo_management";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	
	public StorageContext() {
		
	}
	
	public static Connection getMysqlConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static void closeMysqlConnection(Connection conn){
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	public double getValue(String sloName, String serviceName, double value) {		
		String query = "SELECT value FROM SLOValues "
				+ "INNER JOIN SLOs ON SLOValues.SLOId = SLOs.Id "
				+ "INNER JOIN Services ON SLOs.ServiceId = Services.Id "
				+ "WHERE SLOs.Name = '" + sloName + "' AND Services.Name='" + serviceName + "' "
				+ "AND SLOValues.Highest>=" + value + " AND SLOValues.Lowest<= " + value + ";";
		System.out.println(query);
		double val = 0.0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				val = rs.getDouble(1);
			}else{
				System.out.println("Empty");
			}
			
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return val;
	}
	
	public static void main(String args[]) {
		System.out.println("Connecting...");
		StorageContext context = new StorageContext();
		double res = context.getValue("Monthly_uptime_percentage", "aws_dynamodb", 141);
		System.out.println(res);
	}
	
}

package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerModel {
	
	private Connection connect() {
		Connection con = null;
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "kishan2000");
		System.out.println("Database is Connected");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return con;
	}
	
	public String readCusService() {
		String output= "";
		try {
			Connection con = connect();
			if(con == null) {
				return ("Errror while connecting with the database");
			}
			
			//Preparing the html table for View
			output = "<table border='1'><tr><th>Customer name</th>"+ 
			         "<th>Customer address</th>"+
					 "<th>Issue</th>"+
			         "<th>Phone no.</th>"+
					 "<th>Status</th>"+
			         "<th>Update</th><th>Remove</th></tr>";
			
			//Retrieving from SQL
			String query = "select * from cust";
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(query);
			
			//iterate through the rows in the results set
			while(rs.next()) {
				String Id = Integer.toString(rs.getInt("Id"));
				String Name = rs.getString("Name");
				String Address = rs.getString("Address");
				String Issue = rs.getString("Issue");
				String TelNo = Integer.toString(rs.getInt("TelNo"));
				String Status = rs.getString("Status");
				
			//Adding into the html table
				output += "<tr><td>" +Name+ "</td>";
				output += "<td>" +Address+ "</td>";
				output += "<td>" +Issue+ "</td>";
				output += "<td>" +TelNo+ "</td>";
				output += "<td>" +Status+ "</td>";
				
			//Making place for the buttons
				output +=  "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-cusid='" + Id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cusid='" + Id + "'></td></tr>";
				
				
			}
			con.close();
			
			//Complete the html code
			output += "</table>";
			}catch (Exception e) {
				output = "Error while reading the data";
				System.err.println(e.getMessage());
			}
			return output;
		}
	
public String insertCusService(String Name, String Address, String Issue, String TelNo, String Status) {
		
		String output= "";
		
		
		try {
			
			Connection con = connect();
			
			if(con == null){
				
				return ("Error while connecting the database");
			}
			
			
			
			String query = "insert into cust(`Id`,`Name`, `Address`, `Issue`, `TelNo`, `Status`)"
					+ "values(?,?,?,?,?,?)";
			
			//Preparing Statement
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			//Binding the values
			preparedstmt.setInt(1, 0);
			preparedstmt.setString(2, Name);
			preparedstmt.setString(3, Address);
			preparedstmt.setString(4, Issue);
			preparedstmt.setInt(5, Integer.parseInt(TelNo));
			preparedstmt.setString(6, Status);
			
			//execute the statement
			preparedstmt.execute();
			con.close();
			
			String newCus = readCusService();
			output = "{\"status\":\"success\", \"data\": \""+ newCus + "\"}";
		} catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}



public String updateCusService(String Id, String Name, String Address, String Issue, String TelNo, String Status){
	String output = "";
	
	try {
		Connection con = connect();
		if(con==null) {
			return ("Error while loading to the database for updating");
		}
		
		//Create a prepared statement
		String query = "UPDATE cust SET Name=?, Address=?, Issue=?, TelNo=?, Status=? WHERE Id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		//Binding values
		preparedStmt.setString(1, Name);
		preparedStmt.setString(2, Address);
		preparedStmt.setString(3, Issue);
		preparedStmt.setInt(4, Integer.parseInt(TelNo));
		preparedStmt.setString(5, Status);
		preparedStmt.setInt(6,Integer.parseInt(Id));
		
		//Execute the statement
		preparedStmt.execute();
		con.close();
		
		String newCus = readCusService();
		output = "{\"status\":\"success\", \"data\": \""+ newCus + "\"}";
	}catch(Exception e) {
		output = "{\"status\":\"error\", \"data\": \"Error while updating the Customer Details.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}


public String deleteCusService(String Id) {
	String output= "";
	try {
		Connection con = connect();
		if(con==null) {
			return ("Error while conencting to the Database for deleting.");
		}
		
		//Create a prepared statement
		String query = "DELETE from cust where Id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		//Binding the values
		preparedStmt.setString(1, Id);
		
		//Execute the statement
		preparedStmt.execute();
		con.close();
		
		String newCus = readCusService();
		output = "{\"status\":\"success\", \"data\": \""+ newCus + "\"}";
		
	}catch(Exception e) {
		output = "{\"status\": \"error\", \"data\": \"Error while deleting the Customer details.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}



}

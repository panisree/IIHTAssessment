package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean addNewPerson(String name, String contact, String email) throws ClassNotFoundException, SQLException {
		String sql = "insert into user (Person_Name,Person_Contact,Person_Email) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, name);
		pstmt.setString(2, contact);
		pstmt.setString(3, email);
		
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
	}
	
	public int createCoronaKit(String personName, String email, String contactNumber, String totalAmount, String deliveryAddress) throws SQLException {
		String sql = "insert into CoronaKit (Person_Name,Person_Contact,Person_Email,Delivery_Address,Amount,Order_Date) values(?,?,?,?,?,?);";
		this.connect();
		
		Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, personName);
		pstmt.setString(2, contactNumber);
		pstmt.setString(3, email);
		pstmt.setFloat(5, Float.parseFloat(totalAmount));
		pstmt.setString(4, deliveryAddress);
		pstmt.setString(6, mysqlDateString);
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		int coronaKitId = 0;
		if (rs.next()) {
			coronaKitId= rs.getInt(1);
		}
		
		pstmt.close();
		this.disconnect();
		return coronaKitId;
		
	}
	
	public boolean createKitDetail(String[] Product_ID, int CoronaKit_ID, String[] cost, String[] Quantity) throws SQLException {
		String sql = "insert into KitDetail (Product_ID,CoronaKit_ID,Quantity,Amount) values(?,?,?,?);";
		this.connect();
		boolean success=false;
		
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		this.jdbcConnection.setAutoCommit(false);
		try {
			
			for(int i=0; i<Product_ID.length;i++) {
				pstmt.setInt(1, Integer.parseInt(Product_ID[i]));
				pstmt.setInt(2, CoronaKit_ID);
				pstmt.setInt(3, Integer.parseInt(Quantity[i]));
				pstmt.setFloat(4, (Float.parseFloat(cost[i])*Integer.parseInt(Quantity[i])));
				pstmt.executeUpdate();
				
			}
			this.jdbcConnection.commit();
			System.out.println("Commited db for KitDetail");
			success=true;
		}
		catch(SQLException e){
			this.jdbcConnection.rollback();
			e.printStackTrace();
		}
		finally {
			
			this.jdbcConnection.setAutoCommit(true);
			System.out.println("Set Auto Commit true");
		}		
		
		pstmt.close();
		this.disconnect();
		return success;
		
	}
	public boolean updateCoronaKit(int coronaKitID,boolean success) throws SQLException {
		String sql = "update CoronaKit set Order_Status=? where CoronaKit_ID=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		if(success)
			pstmt.setString(1, "Completed");
		else
			pstmt.setString(1, "Failed");
		
		pstmt.setInt(2, coronaKitID);
		
		boolean updated = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return updated;
	}
}
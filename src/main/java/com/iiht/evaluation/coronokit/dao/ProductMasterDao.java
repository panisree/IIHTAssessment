package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public List<ProductMaster> getProducts() throws ClassNotFoundException, SQLException {
		String sql = "select * from products";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// map it to model
		List<ProductMaster> productdetails = new ArrayList<ProductMaster>();
		while(rs.next()) {
			ProductMaster productdetail = new ProductMaster(rs.getInt("Product_ID"), 
											 rs.getString("Product_Name"), 
											 rs.getFloat("Product_Cost"),
											 rs.getString("Product_Desc"));
			productdetails.add(productdetail);		
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return productdetails;
	}

	public boolean deleteProduct(String id) throws SQLException {
		String sql = "delete from products where Product_ID=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		
		boolean deleted = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return deleted;
		
	}
	
	public boolean editProduct(String id,String productName, String productCost, String productDesc) throws SQLException {
		String sql = "update products set Product_Name=?,Product_Cost=?,Product_Desc=? where Product_ID=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, productName);
		pstmt.setFloat(2, Float.parseFloat(productCost));
		pstmt.setString(3, productDesc);
		pstmt.setInt(4, Integer.parseInt(id));
		
		boolean updated = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return updated;
		
	}
	
	public ProductMaster getProductDetails(String id) throws SQLException {
		String sql = "select * from products where Product_ID="+id;
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ProductMaster productdetail = null;
		while(rs.next()) {
			 productdetail = new ProductMaster(rs.getInt("Product_ID"), 
											 rs.getString("Product_Name"), 
											 rs.getFloat("Product_Cost"),
											 rs.getString("Product_Desc"));
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return productdetail;
	}

	public boolean addProduct(String id,String productName, String productCost, String productDesc) throws SQLException {
		String sql = "insert into products (Product_Name,Product_Cost,Product_Desc) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, productName);
		pstmt.setInt(2, Integer.parseInt(productCost));
		pstmt.setString(3, productDesc);
		
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
		
	}
}
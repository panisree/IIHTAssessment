package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster; 

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		List<ProductMaster> productdetails = this.productMasterDao.getProducts();
		// put data into request object (to share with view page)
		request.setAttribute("productdetails", productdetails);
		
		return "listproducts.jsp";
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pname=request.getParameter("pname");
		String pcost=request.getParameter("pcost");
		String pdesc=request.getParameter("pdesc");
		this.productMasterDao.editProduct(id, pname, pcost, pdesc);
		return "admin?action=list";
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		ProductMaster productdetail=this.productMasterDao.getProductDetails(id);
		request.setAttribute("productdetail", productdetail);
		
		return "editproduct.jsp";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		boolean success=this.productMasterDao.deleteProduct(id);
		
		String returnURL;
		if(success)
			returnURL="admin?action=list";
		else
			returnURL="errorPage.jsp";
		return returnURL;
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String pname=request.getParameter("pname");
		String pcost=request.getParameter("pcost");
		String pdesc=request.getParameter("pdesc");
		boolean success=this.productMasterDao.addProduct(id, pname, pcost, pdesc);			
		
		String returnURL;
		if(success)
			returnURL="admin?action=list";
		else
			returnURL="errorPage.jsp";
		return returnURL;
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("loginid");
		String password=request.getParameter("password");
		String returnURL;
		if(username.equals("admin") && password.equals("admin"))
			returnURL="admin?action=list";
		else
			returnURL="errorPage.jsp";
		
		return returnURL;
	}

	
}
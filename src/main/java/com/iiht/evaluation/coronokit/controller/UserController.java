package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private CoronaKit ckit=new CoronaKit();

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
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

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String[] pname=request.getParameterValues("pname");
		String[] pcost=request.getParameterValues("pcost");
		String[] pqty=request.getParameterValues("Qty");
		String[] id=request.getParameterValues("idvalue");
		String person = request.getParameter("person");
		String address = request.getParameter("address");
		
		String totalCost= (String) request.getParameter("totalCost");
		
		request.setAttribute("pname", pname);
		request.setAttribute("pcost", pcost);
		request.setAttribute("Qty", pqty);
		request.setAttribute("idvalue", id);
		request.setAttribute("person", person);
		request.setAttribute("totalCost", totalCost);
		request.setAttribute("address", address);
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String[] pname=request.getParameterValues("pname");
		String[] pcost=request.getParameterValues("pcost");
		String[] pqty=request.getParameterValues("Qty");
		String[] id=request.getParameterValues("idvalue");
		String person = request.getParameter("person");
		String address = request.getParameter("address");
		String totalCost= (String) request.getParameter("totalCost");
		String[] personDetails=person.split(",");
		
		int coronaKitID=this.kitDAO.createCoronaKit(personDetails[0], personDetails[2], personDetails[1], totalCost, address);
		
		boolean kitDetailSuccess=this.kitDAO.createKitDetail(id, coronaKitID, pcost,pqty);
		if(kitDetailSuccess) {
			this.kitDAO.updateCoronaKit(coronaKitID, kitDetailSuccess);
			request.setAttribute("coronaKitID", coronaKitID);
			return "ordersuccessful.jsp";
		}
		else		
		return "error.jsp";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		String[] pname=request.getParameterValues("pname");
		String[] pcost=request.getParameterValues("pcost");
		String[] pqty=request.getParameterValues("Qty");
		String[] id=request.getParameterValues("idvalue");
		String person = request.getParameter("person");
		
		
		String totalCost= (String) request.getParameter("totalCost");
		
		request.setAttribute("pname", pname);
		request.setAttribute("pcost", pcost);
		request.setAttribute("Qty", pqty);
		request.setAttribute("idvalue", id);
		request.setAttribute("person", person);
		request.setAttribute("totalCost", totalCost);
		
		
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String[] pname=request.getParameterValues("pname");
		String[] pcost=request.getParameterValues("pcost");
		String[] pqty=request.getParameterValues("Qty");
		String[] id=request.getParameterValues("idvalue");
		String person = request.getParameter("person");
		
		
		
		request.setAttribute("pname", pname);
		request.setAttribute("pcost", pcost);
		request.setAttribute("Qty", pqty);
		request.setAttribute("idvalue", id);
		request.setAttribute("person", person);
		
		return "showkit.jsp";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		List<ProductMaster> productdetails = this.productMasterDao.getProducts();
		String person=request.getParameter("person");
		// put data into request object (to share with view page)
		request.setAttribute("productdetails", productdetails);
		request.setAttribute("person", person);
		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String Person_Name=request.getParameter("pname");
		String Person_Contact=request.getParameter("pcontact");
		String Person_Email=request.getParameter("email");
		boolean success=this.kitDAO.addNewPerson(Person_Name, Person_Contact, Person_Email);
		
		String user=Person_Name+","+Person_Contact+","+Person_Email;
		
		
		String returnURL;
		if(success)
			returnURL="user?action=showproducts&person="+user;
		else
			returnURL="errorPage.jsp";
		return returnURL;
	}

}
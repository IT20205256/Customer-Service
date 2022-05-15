package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CusAPI")
public class CusAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerModel cusObj = new CustomerModel();
	
	private static Map<String, String> getParamsMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next(): "";
			
			scanner.close();
			
			String[] params = queryString.split("&");
			for(String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch(Exception e) {
		}
		return map;
	}
       
    public CusAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String output = cusObj.insertCusService(request.getParameter("Name"),
				                                request.getParameter("Address"),
				                                request.getParameter("Issue"),
				                                request.getParameter("TelNo"),
				                                request.getParameter("Status"));
		response.getWriter().write(output);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> paras = getParamsMap(request);
		
		String output = cusObj.updateCusService(paras.get("hidCusIDSave").toString(),
				                                paras.get("Name").toString(), 
				                                paras.get("Address").toString(), 
				                                paras.get("Issue").toString(), 
				                                paras.get("TelNo").toString(), 
				                                paras.get("Status").toString());
		
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> paras = getParamsMap(request);
		
		String output = cusObj.deleteCusService(paras.get("Id").toString());
		
		response.getWriter().write(output);
	}

}

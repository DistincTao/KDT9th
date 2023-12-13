package com.jspbasic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.dto.ProductDTO;

@WebServlet("/inputProd.do")
public class inputProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public inputProd() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		response.setContentType("text/html; charset='utf-8'");
		ProductDTO product = new ProductDTO(name, color, qty, price);
		request.setAttribute("productInfo", product);
		
		RequestDispatcher rd = request.getRequestDispatcher("productOutput.jsp");
		rd.forward(request, response);
			
	}

}

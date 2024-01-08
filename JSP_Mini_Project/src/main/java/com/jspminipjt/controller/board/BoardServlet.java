package com.jspminipjt.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.service.BoardService;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("*.bo")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardServlet 호출됨");
		doService(request,response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// duplicateUserId.mem
		// registerMember.mem
		
		System.out.println("요청한 페이지 : " + request.getRequestURL());
		System.out.println("요청한 페이지 : " + request.getRequestURI());
		System.out.println("요청한 통신 방식 : " + request.getMethod());
		System.out.println("요청한 경로 : " + request.getContextPath());
		
		// 요청된 서블릿 매핑 주소를 통해서 기능을 분류
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
//		String commend = requestURI.replace(contextPath, "");
		String command = requestURI.substring(contextPath.length());
		System.out.println("최종 요청된 서비스 : " + command);
		
		BoardFactory bf = BoardFactory.getInstance();
		BoardService service = bf.getService(command);
		if (service != null) {
			bf = service.doAction(request, response);
		}
		if (bf != null && bf.isRedirect()) {
			response.sendRedirect(bf.getWhereToGo());
		}
		
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardServlet 호출됨");
		doService(request,response);
	}

}

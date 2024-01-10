package com.jspminipjt.controller.member;

import java.io.IOException;
import java.lang.reflect.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.service.MemberService;

@WebServlet("*.mem")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberServlet 호출됨");
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
		
		MemberFactory mf = MemberFactory.getInstance();
		MemberService service = mf.getService(command);
		if (service != null) {
			mf = service.executeService(request, response);
		}
		if (mf != null && mf.isRedirect()) {
			response.sendRedirect(mf.getWhereToGo());
		}
		
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberServlet 호출됨");
		doService(request,response);
	}

}

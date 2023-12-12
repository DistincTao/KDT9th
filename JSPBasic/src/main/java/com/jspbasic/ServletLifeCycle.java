package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/lifecycle")
public class ServletLifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int initCnt = 1;
	int doGetCnt = 1;
	int destroyCnt = 1;
	
    public ServletLifeCycle() {
        super();
    }


	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(this.getClass().getName());
		System.out.println("init 메소드가 호출됨 : " + this.initCnt++ + "회");
		System.out.println("변경 발생");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메소드가 호출됨 : " + this.doGetCnt++ + "회");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드가 호출됨 : " + this.destroyCnt++ + "회");
	}

}

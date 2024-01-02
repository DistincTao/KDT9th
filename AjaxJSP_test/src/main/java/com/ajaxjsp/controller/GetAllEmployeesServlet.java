package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.JsonParsing;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.EmployeeVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/getAllEmployees.do")
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllEmployeesServlet() {
        super();
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
//		response.setContentType("application/json; charset=utf-8");
//		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();
//		PrintWriter out = response.getWriter();
//		
//		try {
////			List<EmployeeVo> list = dao.selectAllEmployees();
//			List<EmployeeVo> list = dao.selectAllEmployees();
//			// 출력 테스트 (dao 단에서 반환되어 왔는지)
//			
//			JsonParsing jsonData = new JsonParsing();
//			String strJson = jsonData.toJsonWithJsonSimple(list);
////			System.out.println(outputJson);
//			
//
//			out.print(strJson);
//			out.close();
//			
////			for (EmployeeVo vo : list) {
////				System.out.println(vo.toString());
////			}
//
//		} catch (SQLException | NamingException e) {
//			out.print(OutputJsonForError.outputJson(e));
//			e.printStackTrace();
//		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		String searchName = request.getParameter("searchName");
		String orderMethod = request.getParameter("orderMethod");
		
		try {
//			List<EmployeeVo> list = dao.selectAllEmployees();
			List<EmployeeVo> list = dao.selectAllEmployees(searchName, orderMethod);
			// 출력 테스트 (dao 단에서 반환되어 왔는지)
			
			// gson으로 json 만들기
//			String strJson = toJsonWithGson(list);
			
			// json simple로 json 만들기
			JsonParsing jsonData = new JsonParsing();
			String strJson = jsonData.toJsonWithJsonSimple(list);
			
			out.print(strJson);
			out.close();
			
//			for (EmployeeVo vo : list) {
//				System.out.println(vo.toString());
//			}
			
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}
	}

//	private String toJsonWithGson(List<EmployeeVo> list) {
//		
//		Map map = new HashMap();
//		map.put("status", "success");
//		
//		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
//		String outputDate = fmt.format(Calendar.getInstance().getTime());
//		map.put("outputDate", outputDate);
//		
//		map.put("count", list.size());
//		map.put("employees", map);
//		
//		Gson gson = new Gson(); // gson 객체 생성
//		String outputGjson = gson.toJson(map); //map을 gson 객체에 넣어서 문자열로 변환
//		
//		return outputGjson;
//	}
	
//	private String toJsonWithGson(List<EmployeeVo> list) {
//		Gson gson = new Gson();
//		
//		Type type = new TypeToken<List<EmployeeVo>>(){}.getType();
//		System.out.println("타입 : " + type.toString());
//		String outputGjson = gson.toJson(list, type);
//		
//		return outputGjson;
//	}
	
}

package com.jspbasic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getParamNames.do")
public class GetParameterNamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetParameterNamesServlet() {

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!");
		
		Enumeration<String> params =request.getParameterNames();
		Map <String, List<String>> valueMap = new HashMap<>();
		
		while (params.hasMoreElements()) { // 다음 요소가 있을 동안 반복 
			String paramName = params.nextElement(); // 넘겨진 parameter의 이름 얻기
//			System.out.println(paramName);
			
			String [] valueArr = request.getParameterValues(paramName);
			System.out.println(paramName + " : " + Arrays.toString(valueArr));
			//---------------------------------------
			List <String> valueList = new ArrayList<>();
			for (String v : valueArr) {
				valueList.add(v);
			}
			System.out.println(paramName + " : " + valueList);
			//---------------------------------------
			valueList = Arrays.asList(valueArr);
			System.out.println(paramName + " : " + valueList);
			//=======================================
			// Map에 값을 담기
			Map <String, String[]> valueMap1 = request.getParameterMap();
			String[] val = valueMap1.get(paramName);
			for (String v : val) {
				System.out.println(paramName + " : " + v);
			}
			//---------------------------------------
			Map <String, String[]> valueMap2 = request.getParameterMap();
			System.out.println(paramName + " : " + Arrays.toString(valueMap2.get(paramName)));
			
			// Map에 name 값을 values 값을 담기
			valueMap.put(paramName, valueList);
		}
		
		// 하나의 값만을 받아와서 출력하고자 할때 사용
		String userIdStr = "";
		if (valueMap.containsKey("userId")){
			List<String> valueUserId = valueMap.get("userId");
			userIdStr = valueUserId.get(0);
		}
		System.out.println("userId" + " : " + userIdStr);
		
		// getParameterMap() 메소드로 값 받아오기
		Map <String, String[]> valueMap3 = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : valueMap3.entrySet()) {
			String key = entry.getKey();
			String [] vals = entry.getValue();
			System.out.println(key + " : " + Arrays.toString(vals));
		}
		
		// 위와 같이 Enumeration 을 활용해서 Map을 생성하고 출력할 수 있고 또는 getParameter()를 이요할 수 도 있다.
		// Enumeration은 인터페이스 이므로 추상적이고, 유연하게 구현해서 쓸 수 있다.
		// Front 단이 바뀌어서 parameter name이 바뀌더라도, 사용할 수 있다.
	}	
}

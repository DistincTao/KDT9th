package com.jspminipjt.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.dao.member.MemberDao;
import com.jspminipjt.dao.member.MemberDaoCRUD;
import com.jspminipjt.etc.OutputJsonForError;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.member.MemberVo;

public class DuplicateUserIdService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("아이디 중복 검사 시작");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("tmpUserId");
//		System.out.println(userId);	
		MemberDao mDao = new MemberDaoCRUD();
		
		Map <String, String> jsonMap = new HashMap<>();
		try {
			MemberVo vo = mDao.duplicateUserId(userId);
//			System.out.println(vo.toString());
			if (vo != null) {
				// 아이디 중복이다. ("isDuplicate" : "true")
				jsonMap.put("isDuplicate", "true");
				jsonMap.put("responseCode", "201");
			} else if (vo == null) {
				jsonMap.put("isDuplicate", "false");
				jsonMap.put("responseCode", "201");
			}
			
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			String outputDate = fmt.format(Calendar.getInstance().getTime());
			jsonMap.put("outputDate", outputDate);
			
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
			jsonMap.put("responseCode", "202");
			jsonMap.put("errMsg", e.getMessage());
		}
		
		JSONObject json = new JSONObject(jsonMap);
		
		out.print(json.toJSONString());
		
		
		out.flush();
		out.close();
		
		return null;
	}

}

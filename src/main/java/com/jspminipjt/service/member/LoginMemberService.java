package com.jspminipjt.service.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.dao.member.MemberDao;
import com.jspminipjt.dao.member.MemberDaoCRUD;
import com.jspminipjt.dao.member.MemberDaoSql;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.member.MemberVo;

public class LoginMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberFactory mf = MemberFactory.getInstance();
		System.out.println("로그인 절차 시작");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		HttpSession sess = request.getSession();
		
		System.out.println(userId + " " + userPwd);
		
		MemberDao dao = MemberDaoCRUD.getInstance();
		int result = -1;
		try {
			MemberVo vo = dao.loginMember(userId, userPwd);
			if (vo != null) { // 로그인 성공
				System.out.println(vo.toString());
				// member 테이블에 포인트를 update하고,pointlog에 기록 남기기
				result = dao.addPointToMember(vo.getUserId(), "login", MemberDaoSql.LOGIN);
				System.out.println("login transaction : " + result);
				vo = dao.loginMember(userId, userPwd);
				if (result == 1) {
					sess.setAttribute("login", vo); // session에 로그인 유저 정보 바인딩				
				}
				
//				request.getRequestDispatcher("../index.jsp").forward(request, response);
				mf.setRedirect(true);
				mf.setWhereToGo(request.getContextPath() + "/index.jsp?status=success");
				
			} else {
				mf.setRedirect(true);
				mf.setWhereToGo(request.getContextPath() + "/member/login.jsp?status=fail");
			}
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mf;
	}

}

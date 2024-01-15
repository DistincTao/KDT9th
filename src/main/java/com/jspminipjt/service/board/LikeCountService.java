package com.jspminipjt.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.dto.board.LikeCountDto;
import com.jspminipjt.service.BoardService;
import com.jspminipjt.vo.LikeCountVo;
import com.jspminipjt.vo.member.MemberVo;

public class LikeCountService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("좋아요 수 증가 진행 시작");
		System.out.println(request.getParameter("boardNo"));
		BoardDao dao = BoardDaoCRUD.getInstance();
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		LikeCountDto dto = null;		
		LikeCountVo LikeVo = null;		

		
		int result = -1;
		MemberVo vo = (MemberVo) sess.getAttribute("login");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String userId = "";
		int likeCount = 0;
//		System.out.println(vo.getUserId() + " " + boardNo + " " + userIp);
		
		try {
			if (vo != null) { 
				userId = vo.getUserId();
				dto = new LikeCountDto(-1, boardNo, userId, null);
				System.out.println(dto.toString());
				result = dao.addLikeCountTransaction(dto);
			} 
			
			if (result == 1) {
				System.out.println("좋아요 수 증가 완료 및 다시 불러오기 시작!");
				json.put("status", "success");
				
				likeCount = dao.selectLikeCount(boardNo);
				json.put("likeCount", likeCount);
				LikeVo = dao.selectLikeLog(boardNo, userId);
				request.setAttribute("likeLog", LikeVo);
				
				
			} else if (result == -1){
				json.put("status", "fail");
			}
			
		} catch (NamingException | SQLException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
			
		out.print(json.toJSONString());
		
		out.flush();
		out.close();
		
		return null;
	}
	
}

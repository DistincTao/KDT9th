package com.jspminipjt.service.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.service.BoardService;
import com.jspminipjt.vo.LikeCountVo;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.board.BoardVo;
import com.jspminipjt.vo.member.MemberVo;

public class GetBoardByNoService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		System.out.println("상세 조회할 게시판 글번호  : " + boardNo);
		BoardFactory bf = BoardFactory.getInstance();
		BoardVo boardVo = null;
		UploadFileVo fileVo = null;
		LikeCountVo likeVo = null;
		HttpSession sess = request.getSession();

		// 클라이어트 ip 주소 얻어오기
		String userIp = getIp(request);
		MemberVo vo = (MemberVo) sess.getAttribute("login");
		int result = -1;
		BoardDao dao = BoardDaoCRUD.getInstance();
		 // 해당 아이피 주소와 글번호가 같은 것이 있으면
		try {
			if (dao.selectReadCountProcess(userIp, boardNo)) { 
				// 24 시간이 지났는지 여부 확인
				if (dao.selectHourDiff(userIp, boardNo) > 23) {
					// 아이피 주소와 글번호와 읽을 시간을 readcountprocess 테이블에서 update
					// 해당 글 번호의 readcount를 update
					result = dao.readCountPocessWithReadCnt(userIp, boardNo, "update");
				}
			} else { 	// 해당 아이피 주소와 글번호가 같은 것이 없으면 (글 최초 조회)
				// 아이피 주소와 글번호와 읽을 시간을 readcountprocess 테이블에서 insert
				// 해당 글 번호의 readcount를 update
				dao.readCountPocessWithReadCnt(userIp, boardNo, "insert");

			}
			if (vo != null ) {
				likeVo = dao.selectLikeLog(boardNo, vo.getUserId());
				if (likeVo != null)
				request.setAttribute("likeLog", likeVo);
			}
				boardVo = dao.selectByBoardNo(boardNo);
				fileVo = dao.getFile(boardNo);
				request.setAttribute("board", boardVo);
				request.setAttribute("file", fileVo);
				request.getRequestDispatcher("/board/viewBoard.jsp").forward(request, response);
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
//			e.getMessage(); // String으로 반환
//			e.getStackTrace(); // Array로 반환
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);

			
		}
		

		return null;
	}

	private String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		System.out.println(">>>> X-Forwarded-For : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println(">>>> Proxy-Client-IP : " + ip);
		}

		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println(">>>> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println(">>>> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println(">>>> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		
		System.out.println(">>>> Result : IP Address : " + ip);

		
		return ip;

	}

}

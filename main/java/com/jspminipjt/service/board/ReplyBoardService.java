package com.jspminipjt.service.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.dto.board.BoardDto;
import com.jspminipjt.service.BoardService;

public class ReplyBoardService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("답글 쓰기 시작");
		BoardFactory bf = BoardFactory.getInstance();
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		int result = -1;
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
//		title = "  ▶" + title;
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		int step = Integer.parseInt(request.getParameter("step"));
		int refOrder = Integer.parseInt(request.getParameter("refOrder"));
		
		
		BoardDto dto = new BoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRef(ref);
		dto.setStep(step);
		dto.setRefOrder(refOrder);
		
		System.out.println(dto.toString());
		
		try {
			result = dao.insertReplyTransaction(dto);
			
			if (result == 1) {
				System.out.println(result + " => 결과");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
		}
		
		bf.setRedirect(true);
		bf.setWhereToGo(request.getContextPath() + "/board/listAll.bo");
		
		return bf;
	}

}

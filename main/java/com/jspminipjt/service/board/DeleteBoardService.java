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
import com.jspminipjt.service.BoardService;

public class DeleteBoardService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println(boardNo + "번 글 삭제 진행 중 ");
		int result = -1;
		
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		try {
			result = dao.deletBoard(boardNo);
			if (result == 1) {
				bf.setRedirect(true);
				bf.setWhereToGo(request.getContextPath()+ "/board/listAll.bo?status=success");
			} else {
				bf.setRedirect(true);
				bf.setWhereToGo(request.getContextPath()+ "/board/viewBoard.jsp?status=fail");
			}
			
		} catch (NamingException | SQLException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		return bf;
	}

}

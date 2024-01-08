package com.jspminipjt.service.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.service.BoardService;
import com.jspminipjt.vo.board.BoardVo;

public class GetEntireBoardService implements BoardService{

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("게시판 전체 목록 조회 시작");
		
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		List<BoardVo> list;
		try {
			list = dao.selectAllBoard();
			System.out.println(list);
			
			if (list.size() == 0) {
				request.setAttribute("boardList", null);
			} else {
				request.setAttribute("boardList", list);
			}
			
			request.getRequestDispatcher("listAll.jsp").forward(request, response);
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

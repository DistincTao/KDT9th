package com.jspminipjt.service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.service.BoardService;

public class LikeCountService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("좋아요 수 증가 진행 시작");
		BoardFactory bf = BoardFactory.getInstance();
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		
		
		
		
		
		
		return bf;
	}

}

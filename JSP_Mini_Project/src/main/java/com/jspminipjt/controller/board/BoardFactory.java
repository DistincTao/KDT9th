package com.jspminipjt.controller.board;

import com.jspminipjt.service.BoardService;
import com.jspminipjt.service.board.GetEntireBoardService;
import com.jspminipjt.service.board.WriteBoardService;

public class BoardFactory {
	private static BoardFactory instance;

	private boolean isRedirect;
	private String whereToGo;

	public BoardFactory () {}

	public static BoardFactory getInstance() {
		if (instance == null) {
			instance = new BoardFactory();
		}

		return instance;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public String getWhereToGo() {
		return whereToGo;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public void setWhereToGo(String whereToGo) {
		this.whereToGo = whereToGo;
	}
	
	public BoardService getService(String command) {
		BoardService result = null;
		
		if (command.equals("/board/listAll.bo")) {
			result = new GetEntireBoardService();
		} else if (command.equals("/board/writeBoard.bo")) {
			result = new WriteBoardService();
		}
		
		return result;
	}
	
}

package com.jspminipjt.controller.board;

import com.jspminipjt.service.BoardService;
import com.jspminipjt.service.board.DeleteBoardService;
import com.jspminipjt.service.board.DeleteLikeCountService;
import com.jspminipjt.service.board.GetBoardByNoService;
import com.jspminipjt.service.board.GetEntireBoardService;
import com.jspminipjt.service.board.LikeCountService;
import com.jspminipjt.service.board.ReplyBoardService;
import com.jspminipjt.service.board.UpdateBoardService;
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
		} else if (command.equals("/board/viewBoard.bo")) {
			result = new GetBoardByNoService();
		} else if (command.equals("/board/updateBoard.bo")) {
			result = new UpdateBoardService();
		} else if (command.equals("/board/deleteBoard.bo")) {
			result = new DeleteBoardService();
		} else if (command.equals("/board/replyBoard.bo")) {
			result = new ReplyBoardService();
		} else if (command.equals("/board/likeCount.bo")) {
			result = new LikeCountService();
		} else if (command.equals("/board/deleteLikeCount.bo")) {
			result = new DeleteLikeCountService();
		}
		
		
		
		return result;
	}
	
}

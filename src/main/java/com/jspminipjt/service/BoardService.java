package com.jspminipjt.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.board.BoardFactory;

public interface BoardService {

	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	
}

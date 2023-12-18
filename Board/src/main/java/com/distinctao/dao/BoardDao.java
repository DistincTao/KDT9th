package com.distinctao.dao;

import java.util.List;

import com.distinctao.dto.BoardDTO;

public interface BoardDao {
	public static final JdbcUtil ju = new JdbcUtil();
	
	public int insert(BoardDTO dto);
	public List<BoardDTO> selectAll();
	public BoardDTO selectNum(int num);
	public int update(int num);
	public int delete(int num);
	
}

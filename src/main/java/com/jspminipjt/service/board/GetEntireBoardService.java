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
import com.jspminipjt.dto.board.SearchCriteriaDto;
import com.jspminipjt.service.BoardService;
import com.jspminipjt.vo.PagingInfoVo;
import com.jspminipjt.vo.board.BoardVo;

public class GetEntireBoardService implements BoardService{

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("게시판 전체 목록 조회 시작");
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		// page 정보 가 없으면 1 페이지를 주고, 페이지 번호가 있으면 그 번호를 대입
		int pageNo = 1;
		if (request.getParameter("pageNo") !=  null && !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		System.out.println(pageNo + " 페이지 글목록을 가져오자!");
		
		// 검색 유형과 검색어를 추가
		System.out.println("searchType : " + request.getParameter("searchType"));
		System.out.println("searchWord : " + request.getParameter("searchWord"));
		String searchType = "";
		String searchWord = "";
		
		if (request.getParameter("searchWord") != null && !request.getParameter("searchWord").equals("")) {  // 검색어가 있는 경우
			searchWord = request.getParameter("searchWord");
		}
		
		if (request.getParameter("searchType") != null && !request.getParameter("searchType").equals("")) {  // 검색어가 있는 경우
			searchType = request.getParameter("searchType");
		}
		
		SearchCriteriaDto searchDto = new SearchCriteriaDto(searchWord, searchType); // dto 객체 생성
		
		System.out.println(searchDto.toString());
		
		
		
		// 일부만 검색하는 것으로 달라져야 함
		PagingInfoVo paging = null;
		try {
			paging = pagingProcess(pageNo, searchDto);
			System.out.println(paging.toString());

			List<BoardVo> list = null;
			if (searchDto.getSearchWord().equals("")) {
				list = dao.selectAllBoard(paging);				
			} else if (!searchDto.getSearchWord().equals("") && !searchDto.getSearchType().equals("") ){
				list = dao.selectAllBoard(paging, searchDto);				
			}
			
			System.out.println(list);
			
			if (list.size() == 0) {
				request.setAttribute("boardList", null);
			} else {
				request.setAttribute("boardList", list);
				request.setAttribute("pageInfo", paging);
			}
			
			request.getRequestDispatcher("listAll.jsp").forward(request, response);
			
		} catch (NamingException | SQLException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
		return null;
	}

	private PagingInfoVo pagingProcess(int pageNo, SearchCriteriaDto dto) throws NamingException, SQLException {
		PagingInfoVo vo = new PagingInfoVo();
		BoardDao dao = BoardDaoCRUD.getInstance();
		vo.setPageNo(pageNo);
		
		if (dto.getSearchWord().equals("")) { // 검색어가 없으면
			// 전제 게시글
			vo.setTotalPostCnt(dao.getTotalPostCnt());
		} else if (!dto.getSearchWord().equals("") && !dto.getSearchType().equals("")) { // 검색어가 있으면
			// 전제 게시글
			vo.setTotalPostCnt(dao.getTotalPostCnt(dto));
		}
		
		// 총 페이지수
		vo.setTotalPageCnt(vo.getTotalPostCnt(), vo.getPagePostCnt());
		// 보이기 시작할 번호
		vo.setStartRowIndex();
		
		// 전체 페이징 블럭 갯수
		vo.setTotalPagingBlock();
		// 현재 페이징 블럭
		vo.setCurrentPageBlock();
		// 현재 페이징 시작 번호
		vo.setStartPageNum();
		// 현재 페이징 마지막 번호
		vo.setEndPageNum();
		
		return vo;
	}

}

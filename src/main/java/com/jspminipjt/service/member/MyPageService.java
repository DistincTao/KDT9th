package com.jspminipjt.service.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.dao.member.MemberDao;
import com.jspminipjt.dao.member.MemberDaoCRUD;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.PagingInfoVo;
import com.jspminipjt.vo.member.MemberPointVo;
import com.jspminipjt.vo.member.MemberVo;

public class MyPageService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 맴버 정보 + pointlog 정보
		MemberFactory mf = MemberFactory.getInstance();
		MemberVo vo = null;
		List<MemberPointVo> list = null;
		String userId = request.getParameter("userId");
		
		System.out.println("조회할 멤버 아이디 : " + userId);
		
		MemberDao dao = MemberDaoCRUD.getInstance();
		// page 정보 가 없으면 1 페이지를 주고, 페이지 번호가 있으면 그 번호를 대입
		int pageNo = 1;
		if (request.getParameter("pageNo") !=  null && !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		PagingInfoVo paging = null;
		try {
			paging = pagingProcess(pageNo, userId);
			System.out.println(paging.toString());
		} catch (NamingException | SQLException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			vo = dao.getMemberInfo(userId);
			list = dao.getMemberPointInfo(userId, paging);
			
			if (list.size() == 0) {
				request.setAttribute("boardList", null);
			} else {
				request.setAttribute("memberInfo", vo);
				request.setAttribute("pointlog", list);
				request.setAttribute("pageInfo", paging);
			}
			
			
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
			
		} catch (SQLException | NamingException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
 		return null;
	}
	
	private PagingInfoVo pagingProcess(int pageNo, String userId) throws NamingException, SQLException {
		PagingInfoVo vo = new PagingInfoVo();
		MemberDao dao = MemberDaoCRUD.getInstance();
		
		vo.setPagePostCnt(15);
		vo.setPageNo(pageNo);
		// 전제 게시글
		vo.setTotalPostCnt(dao.getTotalPostCnt(userId));
//		vo.setTotalPageCnt();
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

package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.db.BoardDAO;
import com.koreait.board4.db.CmtDAO;
import com.koreait.board4.vo.BoardListModel;
import com.koreait.board4.vo.CmtVO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/boardList")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int recordCnt = 7;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = Utils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;//꼭해줘야한다 안해주면 밑에꺼 다 실행 되어 버린다.
		}
		BoardListModel bm = new BoardListModel();
		bm.setI_user(loginUser.getI_user());//로그인한 사람의 i_user
		
		//페이징
		String strPage = request.getParameter("page");
		int page = 1;
		if(strPage != null) {
			page=Integer.parseInt(strPage);
		}
		request.setAttribute("totalPageCnt", BoardDAO.selectTotalPageCnt(recordCnt));
		
		int endIdx = page * recordCnt;
		int startIdx = endIdx - recordCnt;
		bm.setEndIdx(endIdx);
		bm.setStarIdx(startIdx);
		//페이징 끝
		
		request.setAttribute("data",BoardDAO.boardList(bm));
		CmtVO vo = new CmtVO();
		vo.setI_board(bm.getI_board());
		request.setAttribute("cnt", CmtDAO.cntCmt(vo));
		request.getRequestDispatcher("/WEB-INF/jsp/boardList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.logout(request);
		response.sendRedirect("/login");
	}
}

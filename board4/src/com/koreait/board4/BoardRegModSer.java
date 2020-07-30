package com.koreait.board4;

import java.io.IOException;	

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.db.BoardDAO;
import com.koreait.board4.vo.BoardVO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/boardRegMod")
public class BoardRegModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/boardRegMod.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		
		System.out.println("i_board: "+i_board);
		System.out.println("title: " + title);
		System.out.println("ctnt: "+ctnt);
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		BoardVO vo= new BoardVO();
		vo.setI_user(loginUser.getI_user()); //로그인한 유저의 값
		vo.setTitle(title);
		vo.setCtnt(ctnt);
		
		
		//등록
		if("".equals(i_board)) {
				BoardDAO.boardReg(vo);
				response.sendRedirect("/boardList");
				return;
			}
		//수정
		int intI_board = Integer.parseInt(i_board);
		vo.setI_board(intI_board);
		System.out.println("i_board:"+intI_board);
		
		
		int result = BoardDAO.boardMod(vo);
		String qStr = "";
		if(result == 0) {
			qStr = "&err=2";
		}
		//Detail페이지에서 에러메시지 출력하기 위해
		String url = String.format("/boardDetail?i_board=%d%s", intI_board, qStr);
		response.sendRedirect(url);
	}
}

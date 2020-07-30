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


@WebServlet("/boardDel")
public class BoardDelSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int intI_board = Integer.parseInt(request.getParameter("i_board"));
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {//loginUser가 아니면 삭제 버튼이 뜨지 않지만 servlet에서도 판별을 해준다.
			response.sendRedirect("/boardList");
			return;
		}
		
		
		BoardVO vo = new BoardVO();
		vo.setI_board(intI_board);
		vo.setI_user(loginUser.getI_user());
		System.out.println("deleteI_board:"+ intI_board);
		
		
		int result = BoardDAO.boardDel(vo);
		if(result ==0) {
			String url = String.format("/boardDetail?_board=%d&err=1", intI_board);
			response.sendRedirect(url);
			return;
		}
		
		
		request.getRequestDispatcher("/boardList").forward(request, response);//Servlet으로 넘기기 때문에
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

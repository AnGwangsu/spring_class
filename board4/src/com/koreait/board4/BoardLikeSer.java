package com.koreait.board4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.db.BoardLikeDAO;
import com.koreait.board4.vo.BoardLikeVO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/boardLike")
public class BoardLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		//기존에 좋아요를 했엇는지 안했었는지 구분하기 위함
		int isLike = Integer.parseInt(request.getParameter("isLike")); //Detail.jsp에서 isLike변수의 if문에 나온 값을 int형으로 담았다.
		
		//로그인한 내 세션 사용하기 위해
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		BoardLikeVO blvo = new BoardLikeVO();
		blvo.setI_board(i_board);
		blvo.setI_user(loginUser.getI_user());
		
		int result = 0;
		if(isLike == 0 ) { //좋아요를 enable해줘야하고
			result=BoardLikeDAO.enableLike(blvo);
		}else { //disable해줘야한다.(좋아요 취소)-한번더 누르면 
			result=BoardLikeDAO.disableLike(blvo);
		}
		
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String res = String.format("{\"result\" :%d", result);//JSON통신,값을 String으로 바로 변환
		out.print(res);
		out.flush();
	}
}

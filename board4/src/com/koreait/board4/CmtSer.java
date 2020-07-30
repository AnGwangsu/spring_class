package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.db.CmtDAO;
import com.koreait.board4.vo.CmtVO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/boardCmt")
public class CmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_cmt = Integer.parseInt(request.getParameter("i_cmt"));
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/boardDetail?i_board="+i_board);
		}
		
		CmtVO cvo = new CmtVO();
		cvo.setI_cmt(i_cmt);
		cvo.setI_user(loginUser.getI_user());
		System.out.println("i_cmt:"+i_cmt);
		
		CmtDAO.deleteCmt(cvo);
		response.sendRedirect("/boardDetail?i_board="+i_board);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		String cmt = request.getParameter("cmt");
		String i_cmt = request.getParameter("i_cmt");
		
		System.out.println("i_board:" + i_board);
		System.out.println("cmt:"+ cmt);
		
		HttpSession hs = request.getSession(); //작성자만 수정하기 위해서
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		
		CmtVO vo = new CmtVO();
		vo.setI_user(loginUser.getI_user());
		vo.setCmt(cmt);
		vo.setI_board(i_board);
		
		//댓글 등록
		if(i_cmt == null) {
			CmtDAO.insertCmt(vo);
			response.sendRedirect("boardDetail?i_board=" + i_board);//i_board값을 받아넣어줄수 잇게
			return;
		}
		//댓글 수정
		int intI_cmt = Integer.parseInt(i_cmt);
		vo.setI_cmt(intI_cmt);
		CmtDAO.modCmt(vo);
		response.sendRedirect("boardDetail?i_board=" + i_board);
		
	}
}

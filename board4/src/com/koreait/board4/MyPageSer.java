package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.db.UserDAO;
import com.koreait.board4.vo.UserChangeVO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/myPage")
public class MyPageSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typ = request.getParameter("typ");
		
		String jsp="/WEB-INF/jsp/";
		switch(typ) {
		case "1":
			jsp +="changePw.jsp";
			break;
		}
		request.getRequestDispatcher(jsp).forward(request, response);//jsp파일을 열기위해
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typ=request.getParameter("typ");
		UserVO loginUser = Utils.getLoginUser(request);
		
		try {
			switch(typ) {
			case "1": //비밀번호 수정
				procTyp1(loginUser, request, response);
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void procTyp1(UserVO loginUser, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String currentPw = request.getParameter("currentPw");
		String changePw = request.getParameter("changePw");
		
		
		UserChangeVO ucvo = new UserChangeVO();
		ucvo.setI_user(loginUser.getI_user());
		ucvo.setCurrentPw(currentPw);
		ucvo.setChangePw(changePw);
		
		int result = UserDAO.changePw(ucvo);
		if(result ==1) {
			Utils.logout(request);
			response.sendRedirect("/login");
			return;
		}
		String msg="";
		switch(result) {
		case 0:
			msg="기존 비밀번호와 일치합니다.";
			break;
		case 2:
			msg="통신 에러 발생";
			break;
		}
		request.setAttribute("msg", msg);
		doGet(request,response);//에러메시지를 출력하고 다시 doGet으로 수정페이지로 돌아감
		
	}

}

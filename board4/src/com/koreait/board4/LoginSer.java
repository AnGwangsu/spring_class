package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.db.UserDAO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	/*
	 * private void autoLogin(HttpServletRequest request) { HttpSession hs =
	 * request.getSession(); UserVO loginUser = new UserVO();
	 * loginUser.setI_user(29); loginUser.setCid("jhk7716"); loginUser.setNm("안광수");
	 * hs.setAttribute("loginUser", loginUser); }
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃이 되기전에 /login으로 이동해서 다시 로그인 되는것을 방지하기 위해서
		/* autoLogin(request); */
		
		HttpSession hs = request.getSession();
		if(hs.getAttribute("loginUser")!=null) {
			response.sendRedirect("/index");
			return;//리턴을 해줘야 밑에 forward가 도는것을 막을 수 있다.
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");
		
		UserVO vo = new UserVO();
		vo.setCid(cid);
		vo.setCpw(cpw);
		
		int result = UserDAO.login(vo);
		request.setAttribute("data", vo);
		if(result == 1) {
			HttpSession hs = request.getSession();
			vo.setCpw(null); //세션을 주고 비밀번호를 지워 정보를 보호
			
			hs.setAttribute("loginUser", vo); //loginUser라는 이름의 세션값을 보내준다. 
			response.sendRedirect("/boardList"); //로그인이 성공하면 메인페이지로 이동
			return;//값을 반환
		}
		String msg = "에러발생";
		switch(result) {
		case 2:
			msg="아이디가 존재하지 않습니다.";
			break;
		case 3:
			msg="비밀번호가 틀립니다.";
			break;
			default :
				break;
		}
		request.setAttribute("msg", msg);
	}
}

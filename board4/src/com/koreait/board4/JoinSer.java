package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board4.db.UserDAO;
import com.koreait.board4.vo.UserVO;


@WebServlet("/join")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/join.jsp").forward(request, response);//jsp파일을 열수있는게 dispatcher밖에 없음
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");
		String nm = request.getParameter("nm");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		UserVO vo = new UserVO();
		vo.setCid(cid);
		vo.setCpw(cpw);
		vo.setNm(nm);
		vo.setGender(gender);
		vo.setEmail(email);
		
		int result = UserDAO.join(vo);
		//가입은 0또는 1이기 때문에 
		if(result ==1 ) {
			response.sendRedirect("/login");
		}else {
			request.setAttribute("data", vo); //가입정보 유지 지워지지 않고
			request.setAttribute("msg", "중복된 아이디가 있습니다.");//아이디 중복 메시지 출력
			doGet(request,response); //위에서 set으로 받았던 정보를 다시 doGet으로 보내서 값을 초기화하는것을 방지
		}
			
	}

}

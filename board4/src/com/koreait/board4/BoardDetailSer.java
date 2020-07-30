package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletContext;
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


@WebServlet("/boardDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser==null) {
			response.sendRedirect("/login");
			return;
		}
		
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		
		System.out.println("detilI_board :" +i_board);
		//디테일 화면을 보기전에 조회수가 올라가게 해야하니까 변수 선언되고 바로 메소드호출
		ServletContext  application = getServletContext();
		Integer lastViewUser = (Integer)application.getAttribute("board" + i_board);	
		//조회수에서 로그인한사람이 그 보드에 들어가지 않았으면 카운트하고 마지막 으로 본사람이 로그인 유저가 아니면 조회수를 올린다.
		if(lastViewUser == null || lastViewUser != loginUser.getI_user()) {			
			BoardDAO.updCntAdd(i_board);
			//그 보드넘버와 로그인 세션을 애트리뷰트 에 넣어준다.
			application.setAttribute("board" + i_board, loginUser.getI_user());
		}
		
		BoardListModel bm = new BoardListModel();
		bm.setI_board(i_board);
		System.out.println("Detaili_board:"+i_board);
		bm.setI_user(loginUser.getI_user()); 
		request.setAttribute("data", BoardDAO.boardDetail(bm));
		
		String typ = request.getParameter("typ");
		String err = request.getParameter("err");
		
		if(err != null) {
			String msg ="";
					switch(err) {
					case "1":
						msg="삭제실패";
						break;
					case "2":
						msg="수정실패";
						break;
					}
					request.setAttribute("msg", msg);
		}
		CmtVO vo = new CmtVO();
		vo.setI_board(i_board);
		request.setAttribute("list", CmtDAO.selectListCmt(vo));	
		String jsp ="/WEB-INF/jsp/boardDetail.jsp";
		if("mod".equals(typ)) { //값이 mod였을때만 RegMod페이지로 감
			jsp="/WEB-INF/jsp/boardRegMod.jsp";
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

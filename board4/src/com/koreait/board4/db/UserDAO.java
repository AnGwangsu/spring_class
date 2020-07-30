package com.koreait.board4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.board4.vo.UserChangeVO;
import com.koreait.board4.vo.UserVO;

public class UserDAO {
	public static int join(UserVO vo) {
		int result = 0;
		Connection con =null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_user4 "
				+" (i_user,cid,cpw,nm,gender,email) "
				+" values "
				+" (seq_user.nextval,?,?,?,?,?)";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getCid());
			ps.setString(2, vo.getCpw());
			ps.setString(3, vo.getNm());
			ps.setString(4, vo.getGender());
			ps.setString(5, vo.getEmail());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
	public static int chkId(String cid) {//axios를 위한 체크메소드
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql =" select count(cid) " 
				+" from t_user4 " 
				+" where cid =? ";
		
		try {
			con =DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, cid);
			rs=ps.executeQuery();
			
			if(rs.next()) { //아이디가 있으면 result는 1
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
		return result;
	}
	
	public static int login(UserVO uvo) {
		int result = 0;
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_user,cpw,nm "
				+" from t_user4 "
				+" where cid=? "; //DB에서 cid에 unique조건을 주었기 때문에 중복이없는 cid를 확인해서 쿼리문작성
		
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, uvo.getCid());
			rs=ps.executeQuery();
			
			if(rs.next()) {//아이디가 있으면 넘어옴
				String dbPw = rs.getNString("cpw");
				if(dbPw.equals(uvo.getCpw())) {
					int dbI_user = rs.getInt("i_user");
					String dbNm = rs.getNString("nm");
					
					uvo.setI_user(dbI_user);
					uvo.setNm(dbNm);
					
					result=1; //로그인성공
				}else {
					result=3;//패스워드 불일치
				}
			}else {
				result=2; //아이디 없음
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
		return result;
	}
	
	public static int changePw(UserChangeVO ucvo) {
		int result = 2;//2:에러발생, 1:수정완료 , 0:기존 비밀번호 사용
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql=" update t_user4 "
				+" set cpw= ? "
				+" where cpw=? and i_user=? ";//그 유저가 누구인지 pk값을 받아온다.
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, ucvo.getChangePw());
			ps.setString(2, ucvo.getCurrentPw());
			ps.setInt(3, ucvo.getI_user());
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
}

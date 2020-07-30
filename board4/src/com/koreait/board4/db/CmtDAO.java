package com.koreait.board4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board4.vo.CmtVO;

public class CmtDAO {
	public static CmtVO insertCmt(CmtVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_board4_cmt "
				+" (i_cmt,i_board,i_user,cmt) "
				+" values "
				+" (seq_cmt.nextval, ?,?,?) ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getI_board());
			ps.setInt(2, vo.getI_user());
			ps.setString(3, vo.getCmt());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return vo;
	}
	public static int deleteCmt(CmtVO vo) {
		int result=0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql=" delete "
				+" from t_board4_cmt  "
				+" where i_cmt=? and i_user = ?";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getI_cmt());
			ps.setInt(2, vo.getI_user());
			result=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}	
		return result;
	}
	public static int modCmt(CmtVO vo) {
		int result=0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql=" update "
				+" set cmt=?, r_dt=sysdate "
				+" where i_cmt=? and i_user=? ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getCmt());
			ps.setInt(2, vo.getI_cmt());
			ps.setInt(3, vo.getI_user());
			result=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
	public static List<CmtVO> selectListCmt(CmtVO vo){
		List<CmtVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		String sql = " select "
				+" A.i_cmt, A.i_user, A.cmt, A.r_dt,B.nm as writeNm "
				+" from t_board4_cmt A "
				+" inner join t_user4 B "
				+" on A.i_user = B.i_user "
				+" where i_board = ? "
				+" order by i_cmt asc ";
		try {
			con = DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1,vo.getI_board());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int i_cmt = rs.getInt("i_cmt");
				int i_user = rs.getInt("i_user");
				String cmt = rs.getString("cmt");
				String r_dt = rs.getString("r_dt");
				String writeNm = rs.getString("writeNm");
				
				CmtVO cvo = new CmtVO();
				cvo.setI_cmt(i_cmt);
				cvo.setI_user(i_user);
				cvo.setCmt(cmt);
				cvo.setR_dt(r_dt);
				cvo.setWriteNm(writeNm);
				
				list.add(cvo);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
		return list;
	}
	public static CmtVO cntCmt(CmtVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		String sql = " select "
				+" count(*) as cnt "
				+" from t_board4_cmt "
				+" where i_board = ? ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getI_board());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				CmtVO cvo = new CmtVO();
				cvo.setCnt(cnt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
		return vo;		
	}
}

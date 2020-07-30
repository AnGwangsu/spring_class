package com.koreait.board4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.koreait.board4.vo.BoardLikeVO;

public class BoardLikeDAO {
	public static int enableLike(BoardLikeVO para) {
		int result =0;
		Connection con = null;
		PreparedStatement ps = null;
		 
		String sql = " insert into t_board4_Like "
				+" (i_board,i_user) "
				+" values(?, ?) ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, para.getI_board());
			ps.setInt(2, para.getI_user());
			result = ps.executeUpdate();  //0또는 1이날아감
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
	public static int disableLike(BoardLikeVO para) {
		int result =0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " delete "
				+" from t_board4_Like "
				+" where i_board = ? and i_user =? ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, para.getI_board());
			ps.setInt(2, para.getI_user());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
}

package com.koreait.board4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board4.vo.BoardListModel;
import com.koreait.board4.vo.BoardVO;

public class BoardDAO {
	public static void boardReg(BoardVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_board4 "
				+" (i_board,title,ctnt,i_user) " //값부분은 ()를 해줘야한다.
				+" values "
				+" (seq_board.nextval,?,?,?) ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getCtnt());
			ps.setInt(3, vo.getI_user());//작성자의 i_user값을 넣어준다. 수정,삭제할때 본인만 하기위해서
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
	}
	public static int boardMod(BoardVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_board4 "
				+" set title =?, ctnt=?, m_dt = sysdate "
				+" where i_board = ? and i_user = ? ";
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getCtnt());
			ps.setInt(3, vo.getI_board());
			ps.setInt(4, vo.getI_user());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
	public static int boardDel(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql2 =" DELETE FROM t_board4_Like WHERE i_board = ? "; 
		String sql = " DELETE "
				+" FROM t_board4 "
				+" WHERE i_board = ? AND i_user = ? ";

		try {
			con = DbCon.getCon();
			con.setAutoCommit(false);
			
			//sql2
			ps=con.prepareStatement(sql2);
			ps.setInt(1, param.getI_board());
			result=ps.executeUpdate();
			ps.close();
			System.out.println("좋아요테이블 삭제 레코드: " + result);
			
			
			
			//sql
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			result = ps.executeUpdate();
			
			
			con.commit();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			if(con != null) {//오류가 나면 롤백
				try {
					con.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbCon.close(con, ps);
		}

		return result;
	}
	public static List<BoardListModel> boardList(BoardListModel bm){
		List<BoardListModel> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		String sql=" select * from ( " +
				" select rownum as RNUM, Z.* "+
				" from ( "+
				" select " + 
				" A.i_board, A.title, A.r_dt, A.cnt, B.i_user, B.nm as userNm , nvl(C.i_user,0) as likeUser  " + 
				" from t_board4 A " + 
				" inner join t_user4 B " + 
				" on A.i_user = B.i_user " + 
				" left join t_board4_like C on A.i_board = C.i_board and C.i_user =? " +
				" order by i_board desc "+
				" ) Z where rownum <= ? "+
				" ) "+
				" where RNUM > ? ";
		try {
			con = DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, bm.getI_user()); //로그인한 사람의 i_user
			ps.setInt(2, bm.getEndIdx());
			ps.setInt(3, bm.getStarIdx());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				//SQL문의 값을 변수에 넣어준다.
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String r_dt = rs.getString("r_dt");
				int i_user = rs.getInt("i_user");
				String userNm = rs.getString("userNm");
				int cnt = rs.getInt("cnt");
				int likeUser = rs.getInt("likeUser");
				
				//변수에 넣은값을 객체에 넣어준다.
				BoardListModel blsm = new BoardListModel();
				blsm.setI_board(i_board);
				blsm.setTitle(title);
				blsm.setR_dt(r_dt);
				blsm.setI_user(i_user);
				blsm.setUserNm(userNm);
				blsm.setCnt(cnt);
				blsm.setLikeUser(likeUser);
				
				list.add(blsm);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
	
		return list;
	}
	public static BoardListModel boardDetail(BoardListModel bm) {
		BoardListModel blm = new BoardListModel();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select "
				+" A.i_board, A.title, A.ctnt, A.r_dt,A.cnt, B.i_user,B.nm as userNm "
				+" ,nvl(C.i_user,0) as likeUser "
				+" from t_board4 A inner join t_user4 B on A.i_user = B.i_user "
				+" left join t_board4_like C on A.i_board = C.i_board and C.i_user = ? "
				+" where A.i_board = ? ";
		try {
			con=DbCon.getCon(); 
			ps=con.prepareStatement(sql);
			ps.setInt(1, bm.getI_user());
			ps.setInt(2, bm.getI_board());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String ctnt = rs.getString("ctnt");
				String r_dt = rs.getString("r_dt");
				int i_user = rs.getInt("i_user");
				int cnt = rs.getInt("cnt");
				int likeUser = rs.getInt("likeUser");
				
				
				blm.setI_board(i_board);
				blm.setTitle(title);
				blm.setCtnt(ctnt);
				blm.setR_dt(r_dt);
				blm.setI_user(i_user);
				blm.setCnt(cnt);
				blm.setLikeUser(likeUser);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps,rs);
		}
		return blm;
	}
	public static void updCntAdd(int i_board) {//조회수 증가 액션
		Connection con =null;
		PreparedStatement ps = null;
		
		String sql =" update t_board4 "
				+" set cnt = cnt +1 "
				+" where i_board = ? ";
		
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, i_board);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
	}
	//페이징 계산식(글개수를 검색해서 나누어 페이지 숫자를 나타내준다.)
	public static int selectTotalPageCnt(int recordCnt) {
		int result = 0;
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select "
				+" ceil(count(i_board)/?) as totalPageCnt"  //ceil이기때문에 뒤에 소수점은 올림처리가 된다. 4.2  -> 5
				+"  from t_board4  ";
		
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, recordCnt);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps, rs);
		}
		return result; //레코드의 값에 따라 result가 레코드의 값이 박힌다.
	}
}

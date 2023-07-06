package kr.co.ovmkas.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.ovmkas.jsp.domain.Board;
import kr.co.ovmkas.jsp.domain.Criteria;
import kr.co.ovmkas.jsp.util.DBClose;
import kr.co.ovmkas.jsp.util.DBConnection;

public class BoardDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs=null;

	public Long insert(Board board) {
		con = DBConnection.getConnection();
		long result = 0;
		String sql = "insert into tbl_board(title, content, writer, category) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4, board.getCategory());
			pstmt.executeQuery();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Board selectOne(Long bno) {
		con = DBConnection.getConnection();
		Board board = null;
		String sql = "select tb.*, (select count(*) from tbl_reply tr where tr.bno  = tb.bno) replyCnt\r\n"
				+ "from tbl_board tb where bno = ?";
		try {
			int i=1;
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(i, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board(
						rs.getLong(i++), 
						rs.getString(i++), 
						rs.getString(i++), 
						rs.getString(i++),
						rs.getDate(i++), 
						rs.getString(i++), 
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getInt(i++));
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

	public List<Board> selectList(Criteria cri) {
		con = DBConnection.getConnection();
		List<Board> boards = new ArrayList<Board>();
		String sql = "";
		int i=1;
		sql += "select tb.*, (select count(*) from tbl_reply tr where tr.bno  = tb.bno) replyCnt\r\n"
				+ "from tbl_board tb "
				+ "where category = ?";
		sql += getSearchSqlBy(cri);
		sql += " order by bno desc limit ? offset ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(i++, cri.getCategory());
			pstmt.setInt(i++, cri.getAmount());
			pstmt.setInt(i++, (cri.getPageNumber() - 1) * cri.getAmount());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i=1;
				Board board = new Board(
						rs.getLong(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getDate(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getInt(i++));
				boards.add(board);
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boards;
	}
	
	

	public void update(Board board) {
		con = DBConnection.getConnection();
		String sql = "update tbl_board set title = ?, content = ?, updatedate = now() where bno=?";
		try {
			// 문장 생성
			// 쿼리를 먼저 날림
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getBno());

			// 문장 처리
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Long bno) {
		con = DBConnection.getConnection();
		// 처리할 sql구문
		String sql = "delete from tbl_board where bno=?";
		try {
			// 문장 생성
			// 쿼리를 먼저 날림
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bno);
			// 문장 처리
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int deleteReply(Long bno) {
		con = DBConnection.getConnection();
		int result=0;
		String sql = "delete from tbl_Reply where bno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bno);
			// 문장 처리
			result = pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

	public int selectListCount(Criteria cri) {
		con = DBConnection.getConnection();
		// 반환 예정 객체
		int count = 0;
		// 처리할 sql구문
		String sql = "select count(*) from tbl_board where category = ?";
		sql += getSearchSqlBy(cri);
		
		// 행을 여러개 가져올땐 while 단일일땐 if
		try {
			// 문장생성
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, cri.getCategory());

			// 결과집합 반환(표형태)
			rs = pstmt.executeQuery();// 결과 집합을 rs에 보낸다 rs는 표형태

			// rs의 상태는 현재 칼럼명을 보고있는상태 next를 하면 다음 행
			// 결과집합을 자바 객체로 변환
			// LIST형태면 while문으로 돌리고 add하면서 넣어준다
			while (rs.next()) {
				// 객체 생성 후 값 바인딩
				count = rs.getInt(1);
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return count;
	}
	
	private String getSearchSqlBy(Criteria cri) {
		String sql = "";
		if (cri.getCheck() != null && cri.getSearch() != null && String.join("", cri.getCheck()).length()>0) {
			
			sql += " and ( ";
			List<String> list = Arrays.asList(cri.getCheck());
			//T C W
			List<String> typeList = list.stream().map(s->" " + s + " like '%" +  cri.getSearch() + "%' ").collect(Collectors.toList());
			sql += String.join(" or ", typeList);
			sql += ")";
		}
		return sql;
	}

}

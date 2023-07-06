package kr.co.ovmkas.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.ovmkas.jsp.domain.Reply;
import kr.co.ovmkas.jsp.util.DBClose;
import kr.co.ovmkas.jsp.util.DBConnection;

public class ReplyDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs=null;
	
	public int insert(Reply reply) {
		con = DBConnection.getConnection();
		int result=0;
		String sql = "insert into tbl_Reply(content, writer, bno) values(?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  reply.getContent());
			pstmt.setString(2,  reply.getWriter());
			pstmt.setLong(3,  reply.getBno());
			result = pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public List<Reply> selectList(Long bno){
		con = DBConnection.getConnection();
		List<Reply> replies = new ArrayList<Reply>();
		String sql="select * from tbl_Reply where bno=?";
		try {
			pstmt = con.prepareStatement(sql);
			int i=1;
			pstmt.setLong(i++, bno);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				i=1;
				Reply reply = new Reply(
						rs.getLong(i++),
						rs.getString(i++),
						rs.getDate(i++),
						rs.getString(i++),
						rs.getLong(i++)
						);
				replies.add(reply);
				System.out.println("리플호출됨");
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return replies;
	}
	
	public Reply selectOne(Long bno) {
		con = DBConnection.getConnection();
		Reply reply = null;
		String sql = "";
		sql = "select * from tbl_Reply where rno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			int i = 1;
			pstmt.setLong(i++, bno);
			rs = pstmt.executeQuery();// 결과 집합을 rs에 보낸다 rs는 표형태
			while (rs.next()) {
				i = 1;
				// 객체 생성 후 값 바인딩
				reply = new Reply(
						rs.getLong(i++), 
						rs.getString(i++), 
						rs.getDate(i++), 
						rs.getString(i++),
						rs.getLong(i++));
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reply;
	}

	// 삭제
	public int delete(Long rno) {
		con = DBConnection.getConnection();
		int result=0;
		String sql = "delete from tbl_Reply where rno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, rno);
			// 문장 처리
			result = pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

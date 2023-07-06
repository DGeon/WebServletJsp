package kr.co.ovmkas.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.ovmkas.jsp.domain.Member;
import kr.co.ovmkas.jsp.util.DBClose;
import kr.co.ovmkas.jsp.util.DBConnection;

public class MemberDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	/**
	 * 회원가입
	 * @param member
	 */
	public void insert(Member member) {
		con = DBConnection.getConnection();
	
		String sql ="insert into tbl_member(id, pw, name) values(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//회원정보수정
	/**
	 * DB에 있는 회원 정보를 수정
	 * @param member
	 */
	public void update(Member member) {
		con = DBConnection.getConnection();
		String sql = "update tbl_member set pw=?, name=? where id=?";
		int idx=1;
		rs=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//회원탈퇴 관련 
	
	
	/**
	 * update tbl_board 탈퇴한회원 으로 변경하는 메소드
	 * @param member
	 */
	public void updateBoard(Member member) {
		con = DBConnection.getConnection();
		System.out.println(member);
		System.out.println(member.getId());
		
		String sql = "update tbl_board set writer='탈퇴한회원' where writer=? ";
				
		rs=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update tbl_reply 탈퇴한회원 으로 변경하는 메소드
	 * @param member
	 */
	public void updateReply(Member member) {
		con = DBConnection.getConnection();
		System.out.println(member);
		System.out.println(member.getId());
		
		String sql = "update tbl_reply set writer='탈퇴한회원' where writer=? ";

		rs=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 회원탈퇴
	 * @param member
	 */
	public void delete(Member member) {
		con = DBConnection.getConnection();
		System.out.println(member);
		System.out.println(member.getId());
		
		String sql = "delete from tbl_member where id=?";
		rs=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Member> selectList() {
		con = DBConnection.getConnection();
		// 반환 예정 객체
		List<Member> members = new ArrayList<Member>();
		Member member=null;
		// 처리할 sql구문
		String sql = "";
		sql = "select * from tbl_member";
		try {
			// 문장생성
			pstmt = con.prepareStatement(sql);

			// 결과집합 반환(표형태)
			rs = pstmt.executeQuery();// 결과 집합을 rs에 보낸다 rs는 표형태

			while (rs.next()) {
				int i = 1;
				// 객체 생성 후 값 바인딩
				member = new Member(
						rs.getString(i++), 
						rs.getString(i++), 
						rs.getString(i++),
						rs.getDate(i++)); 
				members.add(member);
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	//로그인
	/**
	 * member id와 pw를 가지고 DB에 있는 멤버를 조회
	 * @param member
	 * @return
	 */
	public Member findBy(Member member) {
		con = DBConnection.getConnection();
		String sql = "select * from tbl_member where id = ? and pw = ?";
		int idx=1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member(rs.getString(idx++), rs.getString(idx++), rs.getString(idx++), rs.getDate(idx++));
			}else {
				member=null;
			}
			DBClose.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	
	
	public static void main(String[] args) {
//		MemberDao dao = new MemberDao();
//		Member member = new Member("ldg5477","a1234");
//		dao.findBy(member);
	}
}

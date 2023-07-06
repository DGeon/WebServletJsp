package kr.co.ovmkas.jsp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Member, board DAO의
 * Conneciton
 * PreparedStatement
 * ResulsSet 에대한 close
 * @author Dgeon
 *
 */
public class DBClose {
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}
}

package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.MemberVo;

public class MemberDao {

	public boolean insert(MemberVo memberVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = " insert " + "   into Member " + " values (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPhone());
			pstmt.setString(3, memberVo.getEmail());
			pstmt.setString(4, memberVo.getPassword());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<MemberVo> getList() {
		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "   select member_no, name, phone, email" + "     from member a" + " order by member_no asc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int member_no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);

				MemberVo vo = new MemberVo();
				vo.setMember_no(member_no);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

	public void insertTest(String name, String phonenum, String email, String password) {

		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phonenum);
		vo.setEmail(email);
		vo.setPassword(password);

		new MemberDao().insert(vo);
	}

	public void getListTest() {
		List<MemberVo> list = new MemberDao().getList();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}
}

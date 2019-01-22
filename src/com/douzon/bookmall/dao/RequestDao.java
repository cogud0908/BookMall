package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.RequestVo;

public class RequestDao {

	public boolean insert(RequestVo RequestVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = " insert " + "   into Request " + " values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, RequestVo.getMember_no());
			RequestVo.setPrice();
			pstmt.setInt(2, RequestVo.getPrice());
			pstmt.setString(3, RequestVo.getAddress());

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

	public List<RequestVo> getList() {
		List<RequestVo> list = new ArrayList<RequestVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "   select a.Request_no, b.name, a.price, a.address"
					+ " from Request a, member b"
					+ " where a.member_no = b.member_no";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int request_no = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				String address = rs.getString(4);

				RequestVo vo = new RequestVo();
				vo.setRequest_no(request_no);
				vo.setName(name);
				vo.setPrice(price);
				vo.setAddress(address);

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
	
	public void insertTest(int member_no, String address) {
		RequestVo vo = new RequestVo();
		vo.setMember_no(member_no);
		vo.setAddress(address);
	
		new RequestDao().insert(vo);
	}

	public void getListTest() {
		List<RequestVo> list = new RequestDao().getList();
		for (RequestVo vo : list) {
			System.out.println(vo);
		}
	}
}

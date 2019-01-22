package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.OrderBookVo;

public class OrderBookDao {

	public List<OrderBookVo> getList() {
		List<OrderBookVo> list = new ArrayList<OrderBookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select c.book_no, title, count"
					+ " from cart a, member b, book c"
					+ " where a.member_no = b.member_no and a.book_no = c.book_no";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int book_no = rs.getInt(1);
				String title = rs.getString(2);
				int count = rs.getInt(3);

				OrderBookVo vo = new OrderBookVo();
				vo.setBook_no(book_no);
				vo.setTitle(title);
				vo.setCount(count);

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

	public static Connection getConnection() throws SQLException {
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
	
	public void getListTest() {
		List<OrderBookVo> list = new OrderBookDao().getList();
		for (OrderBookVo vo : list) {
			System.out.println(vo);
		}
	}
}

package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.CartVo;

public class CartDao {

	public boolean insert(CartVo cartVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		boolean result = false;
		try {
						
			conn = getConnection();
			String sql = " insert " + "   into Cart " + " values (? , ? , ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cartVo.getMember_no());
			pstmt.setInt(2, cartVo.getBook_no());
			pstmt.setInt(3, cartVo.getCount());
			
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

	public List<CartVo> getList() {
		List<CartVo> list = new ArrayList<CartVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select b.name, title, count, count*price " + 
					"from cart a, member b, book c " + 
					"where a.member_no = b.member_no and a.book_no = c.book_no";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				int count = rs.getInt(3);
				int result = rs.getInt(4);

				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setTitle(title);
				vo.setCount(count);
				vo.setResult(result);

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
	
	public void insertTest(int member_no, int book_no, int count) {
		CartVo vo = new CartVo();
		vo.setMember_no(member_no);
		vo.setBook_no(book_no);
		vo.setCount(count);
		
		new CartDao().insert(vo);
	}

	public void getListTest() {
		List<CartVo> list = new CartDao().getList();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}
}

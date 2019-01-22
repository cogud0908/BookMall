package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVo;

public class BookDao {

	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = " insert " + "   into book " + " values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setInt(2, bookVo.getPrice());
			
			if(bookVo.getCategory().equals("소설"))
				bookVo.setCategory_no(1);
			else if(bookVo.getCategory().equals("수필"))
				bookVo.setCategory_no(2);
			else if(bookVo.getCategory().equals("컴퓨터/IT"))
				bookVo.setCategory_no(3);
			else if(bookVo.getCategory().equals("예술"))
				bookVo.setCategory_no(4);
			
			pstmt.setInt(3, bookVo.getCategory_no());

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

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "   select book_no, a.title, b.name, a.price" + "     from book a, category b"
					+ "    where a.category_no = b.category_no" + " order by a.book_no asc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int book_no = rs.getInt(1);
				String title = rs.getString(2);
				String category = rs.getString(3);
				int price = rs.getInt(4);

				BookVo vo = new BookVo();
				vo.setBook_no(book_no);
				vo.setTitle(title);
				vo.setCategory(category);
				vo.setPrice(price);

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

	public void insertTest(String title, String category, int price) {
		BookVo vo = new BookVo();
		
		vo.setCategory(category);
		vo.setTitle(title);
		vo.setPrice(price);
		
		new BookDao().insert(vo);
	}
	
	public void getListTest() {
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo: list) {
			System.out.println(vo);
		}
	}
	
}

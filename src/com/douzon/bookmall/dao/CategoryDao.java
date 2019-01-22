package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.CategoryVo;

public class CategoryDao {

	public boolean insert(CategoryVo categoryVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			String sql = " insert " + "   into category " + " values (null, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, categoryVo.getName());

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

	public List<CategoryVo> getList() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "   select Category_no, name" + "     from Category a"
					+" order by a.Category_no asc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int category_no = rs.getInt(1);
				String category = rs.getString(2);

				CategoryVo vo = new CategoryVo();
				vo.setCategory_no(category_no);
				vo.setName(category);

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
	
	public void insertTest(String category) {
		CategoryVo vo = new CategoryVo();
		vo.setName(category);

		new CategoryDao().insert(vo);
	}

	public void getListTest() {
		List<CategoryVo> list = new CategoryDao().getList();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
}

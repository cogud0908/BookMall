package com.douzon.bookmall.vo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.douzon.bookmall.dao.CartDao;

public class RequestVo {

	private int request_no;
	private int member_no;
	private String name;
	private int list_no;
	private int price;
	private String address;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRequest_no() {
		return request_no;
	}
	public void setRequest_no(int request_no) {
		this.request_no = request_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getList_no() {
		return list_no;
	}
	public void setList_no(int list_no) {
		this.list_no = list_no;
	}
	public int getPrice() {
		return price;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	
	public void setPrice() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = CartDao.getConnection();
			stmt = conn.createStatement();
			String sql = "select sum(b.price * a.count) " + 
					"from cart a, book b, member c " + 
					"where a.book_no = b.book_no " + 
					"and a.member_no = c.member_no " + 
					"and a.member_no = "+member_no;
			
			rs = stmt.executeQuery(sql);
						
			while (rs.next()) {
				price = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
	}
		
	@Override
	public String toString() {
	
		return "[" + name + "] "+ price +" / "+address;
	}
	
}

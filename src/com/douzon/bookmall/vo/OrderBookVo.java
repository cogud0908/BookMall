package com.douzon.bookmall.vo;

public class OrderBookVo {

	private int orderbook_no;
	private int request_no;
	private String name;
	private String title;
	private int count;
	private int book_no;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRequest_no() {
		return request_no;
	}
	public void setRequest_no(int request_no) {
		this.request_no = request_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOrderbook_no() {
		return orderbook_no;
	}
	public void setOrderbook_no(int orderbook_no) {
		this.orderbook_no = orderbook_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	@Override
	public String toString() {
		
		return "["+book_no+"] "+title + " / " + count;
	}
}

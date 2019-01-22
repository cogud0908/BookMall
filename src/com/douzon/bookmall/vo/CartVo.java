package com.douzon.bookmall.vo;

public class CartVo {

	private int cart_no;
	private int book_no;
	private int member_no;
	private String title;
	private String name;
	private int count;
	private int result;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;		
	}
		
	@Override
	public String toString() {
		return "["+name +"] "+title +" / " + count + " / "+result;
	}
}

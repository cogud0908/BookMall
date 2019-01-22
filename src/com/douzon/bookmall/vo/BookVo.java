package com.douzon.bookmall.vo;

public class BookVo {

	private int book_no;
	private int category_no;
	private String category;
	private String title;
	private int price;
	
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
	
	@Override
	public String toString() {
		return "[" + book_no + "] " + title + " / " + category + " / " + price;
	}
}

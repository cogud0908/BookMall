package com.douzon.bookmall.vo;

public class CategoryVo {

	private int category_no;
	private String name;
	
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "[" + category_no + "] " + name;
	}
}

package com.douzon.bookmall.main;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.dao.OrderBookDao;
import com.douzon.bookmall.dao.RequestDao;

public class BookMall {

	public static void main(String[] args) {
						
		MemberDao memberDao = new MemberDao();
		BookDao bookDao = new BookDao();
		CartDao cartDao = new CartDao();
		CategoryDao cartegoryDao = new CategoryDao();
		OrderBookDao orderBookDao = new OrderBookDao();
		RequestDao requestDao = new RequestDao();
		
		// 1. 회원 리스트 – 2명
		System.out.println("===============사용자 목록===============");
		memberDao.insertTest("임채형", "010-5299-0701", "cogud0908@naver.com", "1234");
		memberDao.insertTest("개발자", "010-1004-1004", "helpme@naver.com", "1004");
		memberDao.getListTest();
		
		System.out.println("===============카테고리 목록===============");
	    // 2. 카테고리 리스트 – 3개
		cartegoryDao.insertTest("소설");
		cartegoryDao.insertTest("수필");
		cartegoryDao.insertTest("컴퓨터/IT");
		cartegoryDao.insertTest("예술");
		cartegoryDao.getListTest();
		
		System.out.println("===============상품 목록===============");
	    // 3. 상품리스트 – 3개
		bookDao.insertTest("이것이 자바다", "컴퓨터/IT",20000);
		bookDao.insertTest("서양 마술사", "예술",13000);
		bookDao.insertTest("안드로이드 완전정복","컴퓨터/IT",40000);
		bookDao.insertTest("아큐정전","소설",10000);
		bookDao.getListTest();
		
		System.out.println("===============카트 목록===============");
	    // 4. 카트 리스트 – 2개
		cartDao.insertTest(1, 2, 1);
		cartDao.insertTest(2, 1, 2);
		cartDao.insertTest(1, 3, 1);
		cartDao.insertTest(2, 4, 2);
		cartDao.getListTest();
		
		System.out.println("===============주문 목록===============");
	    // 5. 주문 리스트 – 1개
		requestDao.insertTest(1, "부산시 해운대구 우동 부산산업진흥위원회 7층 격물기지 강의장");
		requestDao.insertTest(2, "부산시 금정구 남산동 부산외국어대학교 I412");
		requestDao.getListTest();		
		
		System.out.println("===============주문도서 목록===============");
	    // 6. 주문 도서 리스트 – 2개
		orderBookDao.getListTest();
		
		
	}

}

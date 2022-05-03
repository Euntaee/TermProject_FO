package com.project.demo.vo;

import lombok.Data;

@Data
public class BookVO {
	private int bookNo;
	private int bookHit;
	private String bookImg;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublish;
	private String bookContent;
	private String bookGenre;
	private String bookRegdate;
	private int totalPage;

	// branch 테이블 조인
	private String branchCode;
	private String branchName;
	private String branchAddr;

	// Stock 테이블 조인
	private int stock_count; 
}

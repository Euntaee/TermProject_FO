package com.project.demo.vo;

import lombok.Data;

@Data

public class RentVO {
	private int rentNo;
	private int rentCheck;
	private int bookNo;
	private String rentStartdate;
	private String rentEnddate;
	private String userId;		
	private String branchCode;
	
	//도서	
	private int bookHit;
	private String bookImg;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublish;
	private String bookContent;
	private String bookGenre;
	private String bookRegdate;
	private int totalPage;
	
	//지점	
	private String branchName;
	private String branchAddr;
}

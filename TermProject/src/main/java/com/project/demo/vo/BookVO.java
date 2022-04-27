package com.project.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}

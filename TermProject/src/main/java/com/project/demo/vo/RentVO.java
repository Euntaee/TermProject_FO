package com.project.demo.vo;

import org.springframework.stereotype.Component;
import lombok.Data;
@Data
@Component
public class RentVO extends BookVO {
	private int rentNo;
	private int rentCheck;
	private int bookNo;
	private String rentStartdate;
	private String rentEnddate;
	private String userId;	
	private BookVO BookVO;

	public RentVO() {
		this.BookVO = new BookVO();
	}

	public BookVO getRentVO() {
		return BookVO;
	}

	public void setRentVO(BookVO BookVO) {
		this.BookVO = BookVO;
	}



}

package com.project.demo.vo;

import org.springframework.stereotype.Component;

@Component
public class RentVO extends BookVO{
private int rent_no;
private int rent_check;
private String rent_startdate;
private String rent_enddate;
private int book_no;
private String user_id;
private BookVO BookVO;

public RentVO() {
	this.BookVO= new BookVO();
}
public BookVO getRentVO() {
	return BookVO;
}
public void setRentVO(BookVO BookVO) {
	this.BookVO= BookVO;
}

public int getRent_no() {
	return rent_no;
}
public void setRent_no(int rent_no) {
	this.rent_no = rent_no;
}
public int getRent_check() {
	return rent_check;
}
public void setRent_check(int rent_check) {
	this.rent_check = rent_check;
}
public String getRent_startdate() {
	return rent_startdate;
}
public void setRent_startdate(String rent_startdate) {
	this.rent_startdate = rent_startdate;
}
public String getRent_enddate() {
	return rent_enddate;
}
public void setRent_enddate(String rent_enddate) {
	this.rent_enddate = rent_enddate;
}
public int getBook_no() {
	return book_no;
}
public void setBook_no(int book_no) {
	this.book_no = book_no;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}

}


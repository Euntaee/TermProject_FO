package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.RentService;
import com.project.demo.vo.RentVO;
import com.project.demo.vo.StockVO;
import com.project.demo.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RentRestController {
	private final RentService rentService;

	@PostMapping("/rent_info")
	public void rentInsert(String book_no, String user_id, String branch_code, String rent_no) {
		Map<String, String> map = new HashMap<>();
		map.put("book_no", book_no);
		map.put("user_id", user_id);
		map.put("branch_code", branch_code);
		map.put("rent_no", rent_no);
		rentService.rentInsert(map);
	}

	@PostMapping("/rent_rule")
	public String rentRule(String user_id) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		String msg = "";
		UserVO vo = new UserVO();
		vo = rentService.rentRestrict(map);
		msg = vo.getMsg();
		return msg;
	}

	@PostMapping("/rent_select")
	public List<RentVO> rentSelect(String user_id, String book_no) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		System.out.println("아이디" + user_id);
		return rentService.rentSelect(map);
	}

	@PostMapping("/rent_return")
	public void rentReturn(String user_id, String book_no, String branch_code, String rent_no) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("book_no", book_no);
		map.put("branch_code", branch_code);
		rentService.rentState(map);
	}

	@PostMapping("/bookStock")
	public String bookStock(String book_no, String branch_code) {		
		Map<String, String> map = new HashMap<>();		
		map.put("book_no", book_no);
		map.put("branch_code", branch_code);
		String result = "";
		StockVO vo = rentService.bookStockCount();
		result = vo.getMsg();
		return result;
	}
}

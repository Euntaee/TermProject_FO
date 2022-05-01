package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.RentService;
import com.project.demo.vo.RentVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RentRestController {
	private final RentService rentService;

	@PostMapping("/rent_info")
	public void rentInsert(String book_no, String user_id) {
		Map<String, String> map = new HashMap<>();
		map.put("book_no", book_no);
		map.put("user_id", user_id);
		System.out.println(user_id);
		System.out.println(book_no);
		System.out.println();
		rentService.rentInsert(map);		
	}

	@PostMapping("/rent_select")
	public List<RentVO> rentSelect(String user_id) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		System.out.println("아이디" + user_id);
		return rentService.rentSelect(map);
	}
}

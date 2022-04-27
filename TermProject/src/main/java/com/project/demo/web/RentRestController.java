package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.RentService;
import com.project.demo.vo.RentVO;

@RestController
public class RentRestController {
	@Autowired
	RentService rentService;
	@RequestMapping("/rent_info")
	public void rent_insert(String book_no, String user_id) {		
		Map map=new HashMap();
		map.put("book_no", book_no);
		map.put("user_id", user_id);
		System.out.println(user_id);
		System.out.println(book_no);
		System.out.println();
		rentService.rentInsert(map);
	}
	
	@RequestMapping("/rent_select")
	public List<RentVO> rent_select(String user_id) {
		Map map=new HashMap();
		map.put("user_id", user_id);		
		System.out.println("아이디"+user_id);
		return rentService.rentSelect(map);
	}
}

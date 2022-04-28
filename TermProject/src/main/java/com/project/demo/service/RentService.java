package com.project.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.project.demo.dao.RentDAO;
import com.project.demo.vo.RentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentService {
	private final RentDAO rentdao;

	public void rentInsert(Map<String, String> map) {
		rentdao.rentInsert(map);
	}

	public List<RentVO> rentSelect(Map<String, String> map) {
		return rentdao.rentSelect(map);
	}
}

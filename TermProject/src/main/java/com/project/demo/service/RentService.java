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
		rentdao.bookIncreaseHit(map);
		rentdao.rentInsert(map);
		rentdao.rentState(map);
	}

	public List<RentVO> rentSelect(Map<String, String> map) {
		return rentdao.rentSelect(map);
	}

	public void rentState(Map<String, String> map) {
		rentdao.rentState(map);
	}
}

package com.project.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.project.demo.dao.RentDAO;
import com.project.demo.vo.RentVO;
import com.project.demo.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentService {
	private final RentDAO rentdao;
	private static final String MSG_OK = "true";
	private static final String MSG_NO = "false";

	public void rentInsert(Map<String, String> map) {
		rentdao.bookIncreaseHit(map);
		rentdao.rentInsert(map);
		rentdao.rentState(map);
	}

	public UserVO rentRestrict(Map<String, String> map) {		
		UserVO vo = new UserVO();
		int count = rentdao.userRentCount(map);
		if (count < 3) {
			vo.setMsg(MSG_OK);
			rentdao.IncreaseUserRent(map);
		} else if(count > 2) {
			vo.setMsg(MSG_NO);
		}
		return vo;
	}

	public List<RentVO> rentSelect(Map<String, String> map) {
		return rentdao.rentSelect(map);
	}

	public void rentState(Map<String, String> map) {
		rentdao.rentState(map);
		rentdao.decreaseUserRent(map);
	}
}

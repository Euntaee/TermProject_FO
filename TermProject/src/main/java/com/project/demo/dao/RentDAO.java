package com.project.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.project.demo.vo.RentVO;
import com.project.demo.vo.StockVO;

@Repository
@Mapper
public interface RentDAO {
	void rentInsert(Map<String, String> map);
	
	List<RentVO> rentSelect(Map<String, String> map);
	
	void bookIncreaseHit(Map<String, String> map);
	
	void rentState(Map<String, String> map);
	
	int userRentCount(Map<String, String> map);
	
	void IncreaseUserRent(Map<String, String> map);
	
	void decreaseUserRent(Map<String, String> map);
	
	void decreaseBookStock(Map<String, String> map);
	
	void increaseBookStock(Map<String, String> map);
	
	void returnDay(Map<String, String> map);
	
	StockVO bookStockCount(Map<String, String> map);
}

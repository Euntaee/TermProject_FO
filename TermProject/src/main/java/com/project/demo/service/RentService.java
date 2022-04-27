package com.project.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dao.RentDAO;
import com.project.demo.vo.RentVO;

@Service
public class RentService {
    @Autowired
    RentDAO rentdao;

    public void rentInsert(Map map) {
        rentdao.rentInsert(map);
    }

    public List<RentVO> rentSelect(Map map) {
        return rentdao.rentSelect(map);
    }
}

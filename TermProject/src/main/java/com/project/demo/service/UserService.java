package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dao.UserDAO;
import com.project.demo.vo.UserVO;

@Service
public class UserService {
	@Autowired
	UserDAO userdao;
	public UserVO isLogin(String user_id, String user_pwd)
    {
    	UserVO vo = new UserVO();
    	int count = userdao.userIdCount(user_id);
    	if(count == 0) 
    	{
    		vo.setMsg("NOID");
    	}
    	else
    	{
    		UserVO dbVO = userdao.userGetPassword(user_id);
    		if(user_pwd.equals(dbVO.getUser_pwd()))
    		{
    			vo.setMsg("OK");
    			vo.setUser_id(user_id);
    			vo.setUser_name(dbVO.getUser_name());    			
    		}
    		else 
    		{
    			vo.setMsg("NOPWD");
    		}
    	}
    	System.out.println(count); 
    	return vo;
    }
	
	public void userInsert(UserVO vo) {
		 userdao.userInsert(vo);
	}
}

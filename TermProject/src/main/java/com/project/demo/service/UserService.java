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
    	if(count == 0) // ID가 없는 상태 
    	{
    		vo.setMsg("NOID");
    	}
    	else
    	{
    		UserVO dbVO = userdao.userGetPassword(user_id);
    		if(user_pwd.equals(dbVO.getUser_pwd())) //  비밀번호가 일치
    		{
    			vo.setMsg("OK");
    			vo.setUser_id(user_id);
    			vo.setUser_name(dbVO.getUser_name());    			
    		}
    		else //비밀번호가 틀릴때
    		{
    			vo.setMsg("NOPWD");
    		}
    	}
    	System.out.println(count);
    	return vo;
    }
}

package com.project.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;
import com.project.demo.dao.UserDAO;
import com.project.demo.util.SHA256Util;
import com.project.demo.vo.UserVO;

@Service
public class UserService {
	@Autowired
	UserDAO userdao;
	public UserVO isLogin(String user_id, String user_pwd){		
    	UserVO vo = new UserVO();
    	int count = userdao.userIdCount(user_id);
    	if(count == 0) {
    		vo.setMsg("NOID");
    	}
    	else{
    		UserVO dbVO = userdao.userGetPassword(user_id);
    		if(user_pwd.equals(dbVO.getUserPwd())){
    			vo.setMsg("OK");
    			vo.setUserId(user_id);
    			vo.setUserName(dbVO.getUserName());    			
    		}
    		else {
    			vo.setMsg("NOPWD");
    		}
    	}
    	System.out.println(count); 
    	return vo;
    }
	
	public void userInsert(UserVO vo) {
		 vo.setUserPwd(SHA256Util.encryptSHA256(vo.getUserPwd()));		 
		 userdao.userInsert(vo);
	}
	
	public UserVO selectUserInfo(String user_id) {
		return userdao.selectUserInfo(user_id);
	}
	
    public UserVO isPwd(String user_pwd, String user_id) {
	    UserVO vo = userdao.userGetPassword(user_id);
	    if(user_pwd.equals(vo.getUserPwd())){
		    vo.setMsg("OK");
			vo.setUserId(user_id);
			vo.setUserName(vo.getUserName());    			
		}else {
			vo.setMsg("NOPWD");
		}
		return vo;
	}
	
	public void changPwd(Map map) {
//		System.out.println("service에서 비밀번호 값:"+user_pwd);
		userdao.changPwd(map);
	}
}

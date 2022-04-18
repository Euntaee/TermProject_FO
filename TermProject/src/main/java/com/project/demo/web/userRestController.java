package com.project.demo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.UserService;
import com.project.demo.vo.UserVO;

@RestController
public class userRestController {	
	@Autowired
	UserService userService;
	@RequestMapping(value = "/login_ok")
	public String member_login_ok( String user_id,String user_pwd, HttpSession session)
	{
		   String msg = "";
		   UserVO vo = userService.isLogin(user_id, user_pwd);
		   if(vo.getMsg().equals("OK"))
		   {
			   session.setAttribute("user_id", vo.getUser_id());
			   session.setAttribute("user_name", vo.getUser_name());			   
		   }
		   msg = vo.getMsg();
		   System.out.println(msg);		   
		   System.out.println((String)session.getAttribute(user_id));
		   return msg;
	}

	@RequestMapping(value="/userInsert")	  
	   public void member_join_ok(UserVO vo)
	   {
//		   vo.setUser_phone(vo.getTel1() + "-" + vo.getTel2() + "-" + vo.getTel3());
		   
		   userService.userInsert(vo);
	   }
}
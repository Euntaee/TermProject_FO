package com.project.demo.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.dao.UserDAO;
import com.project.demo.vo.UserVO;

@Controller
public class UserController {
@Autowired
private UserDAO dao;

@PostMapping("/login_ok")
@ResponseBody
public String member_login_ok(String id, String pwd, HttpSession session)
{
	   String msg = "";
	   UserVO vo = dao.isLogin(id, pwd);
	   if(vo.getMsg().equals("OK"))
	   {
		   session.setAttribute("id", vo.getId());
		   session.setAttribute("name", vo.getName());
		   session.setAttribute("admin", vo.getAdmin());
	   }
	   msg = vo.getMsg();
	   return msg;
}
}

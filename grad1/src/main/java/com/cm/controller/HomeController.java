package com.cm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.service.IMenuService;
import com.cm.service.IUserService;
import com.cm.vo.UserVO;
import com.jcraft.jsch.Session;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	public IUserService iUserService;//ʵ����
	
	@Autowired
	public IMenuService iMenuService;

	@RequestMapping("/toHomeUI.do")//����homeҳ��
	public String toHomeUI(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return "home";
	}
	
	@RequestMapping("/toLoginUI.do")//���ص�¼ҳ��
	public String toLoginUI(HttpServletRequest request,HttpServletResponse response){
		return "login2";
	}
	
	@RequestMapping("/logOut.do")//�˺��˳�
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		//���session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "login2";
	}
}

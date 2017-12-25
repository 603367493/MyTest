package com.cm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cm.service.IMenuService;
import com.cm.service.IUserService;
import com.cm.vo.UserVO;

import sun.awt.HToolkit;
import sun.misc.UUDecoder;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	public IUserService iUserService;
	
	@Autowired
	public IMenuService iMenuService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestParam String acc, @RequestParam String pwd,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		Map<String,Object> map =  iUserService.login(acc, pwd);
		//登录失败
		if(map.get("flag").toString().equals("true")){
			//user对象放进session里面
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60*24);
			UserVO user = (UserVO) map.get("user");
			session.setAttribute("user", user);	
			
			//更新菜单栏的数据
			iMenuService.updateMenuInfo(user);
			
			//更新上次登录时间
			System.out.println("更新上次登录时间中...");
			Map<String,Object> updateTimeMap = new HashMap<String,Object>();
			updateTimeMap.put("last_login_time", new Date(System.currentTimeMillis()));
			updateTimeMap.put("id", user.getId());
			iUserService.updateLoginTime(updateTimeMap);
		}

		return map;
	}
	
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updatePwd(int user_id,String pwd){
		return iUserService.updatePwd(pwd, user_id);
	}
	
	@RequestMapping("toUserManagerUI")
	public String toUserManagerUI(HttpServletRequest request){
		return "userManager";
	}
	@RequestMapping("getUserList")
	@ResponseBody
	public List<UserVO> getUserList(HttpServletRequest request){
		return iUserService.getUserList();
	}
	
	@RequestMapping("toAddUserUI")
	public String toAddUserUI(HttpServletRequest request,Integer flag,@RequestParam(required=false)Integer id){
		if(flag != 1){
			UserVO user = iUserService.getUserById(id);
			request.setAttribute("user1", user);
		}
		request.setAttribute("flag", flag);
		return "addUser";
	}
	
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addUser(@RequestBody UserVO userVO,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		userVO.setPower(2);
		userVO.setImage_url("img/defalut.png");
		userVO.setPublish_name(user.getName());
		userVO.setPublish_time(new Date(System.currentTimeMillis()));
		userVO.setPublish_id(user.getId());
		return iUserService.add(userVO);
	}
	

	@RequestMapping(value="deleteUser")
	@ResponseBody
	public Map<String,Object> deleteUser(int id){
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setDelete_flag(1);
		userVO.setPower(2);
		return iUserService.deleteUser(userVO);
	}
	
	@RequestMapping("toAddPriceUI")
	public String toAddPriceUI(HttpServletRequest request,@RequestParam(required=false)Integer user_id){
		request.setAttribute("user_id", user_id);
		return "addPrice";
	}
	
	@RequestMapping("addPrice")
	@ResponseBody
	public Map<String,Object> deleteUser(@RequestBody UserVO userVO){
		
		return iUserService.updatePrice(userVO);
	}
	
	@RequestMapping("toCphManagerUI.do")
	public String toCphManagerUI(HttpServletRequest request){
		return "cphManager";
	}
}

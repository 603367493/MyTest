package com.cm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.cm.service.IAccountService;
import com.cm.vo.FamilyVO;
import com.cm.vo.UserVO;
/**
 * 账号控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="account")
public class AccountController {

	@Autowired
	public IAccountService iAccountService;
	
	@RequestMapping(value="getFamilyList")
	@ResponseBody
	//设置
	public List<FamilyVO> getAccount(HttpServletResponse response,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iAccountService.getFamilyList(user.getId());
	}
	//返回账号页面
	@RequestMapping("toAccountUI")
	public String toAccountUI(HttpServletResponse response,HttpServletRequest request){
		return "account";
	}
	//返回家属页面
	@RequestMapping("toFamliyManagerUI")
	public String toFamliyManagerUI(HttpServletResponse response,HttpServletRequest request){
		return "familyManager";
	}
	//设置家属属性
	@RequestMapping("getFamilyById")
	@ResponseBody
	public List<FamilyVO> getFamilyById(HttpServletResponse response,HttpServletRequest request,int user_id){
		return iAccountService.getFamilyList(user_id);
	}
	//返回添加家属窗
	@RequestMapping("toAddFamilyUI")
	public String toAddFamilyUI(HttpServletResponse response,HttpServletRequest request,
			int user_id,Integer flag,@RequestParam(required=false)Integer id){
		if(flag != 1){
			FamilyVO familyVO = iAccountService.getFamilyById(id);
			request.setAttribute("familyVO", familyVO);
		}
		request.setAttribute("flag", flag);
		request.setAttribute("user_id", user_id);
		return "addFamily";
	}
	//添加家属属性
	@RequestMapping(value="addFamily",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addFamily(@RequestBody FamilyVO familyVO,HttpServletRequest request){
		System.out.println(JSON.toJSONString(familyVO));
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		familyVO.setPublish_name(user.getName());
		familyVO.setPublish_time(new Date(System.currentTimeMillis()));
		familyVO.setPublish_id(user.getId());
		return iAccountService.addFamily(familyVO);
	}
	
//删除家属属性
	@RequestMapping(value="deleteFamily")
	@ResponseBody
	public Map<String,Object> deleteUser(int id){
		FamilyVO familyVO = new FamilyVO();
		familyVO.setId(id);
		familyVO.setDelete_flag(1);
		return iAccountService.deleteFamily(familyVO);
	}
}

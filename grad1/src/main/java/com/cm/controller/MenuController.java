package com.cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.common.PropertiesUtils;
import com.cm.service.IMenuService;
import com.cm.vo.MenuVO;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	public IMenuService iMenuService;

	@RequestMapping("/getMenu.do")
	@ResponseBody//返回权限
	public List<MenuVO> getMenu(HttpServletRequest request,HttpServletResponse response,int power){
/*		String value=PropertiesUtils.getValue("/config/initData.properties", "username");
		System.out.println(value);*/
		return iMenuService.getMenu(power);
	}
	
	@RequestMapping("/getMenuById.do")
	@ResponseBody
	public List<MenuVO> getMenuById(HttpServletRequest request,HttpServletResponse response,int id,int power){
		return iMenuService.getMenuById(id,power);
	}
	
	@RequestMapping("/getMenuByParentId.do")
	@ResponseBody//获得parentid
	public List<MenuVO> getMenuByParentId(HttpServletRequest request,HttpServletResponse response,int parent_id,int power){
		return iMenuService.getMenuByParentId(parent_id,power);
	}
}	


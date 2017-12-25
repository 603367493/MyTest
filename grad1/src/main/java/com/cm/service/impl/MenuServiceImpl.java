package com.cm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cm.common.BaseDAO;
import com.cm.service.IMenuService;
import com.cm.vo.MenuVO;
import com.cm.vo.NewsVO;
import com.cm.vo.UserVO;

@Service
public class MenuServiceImpl implements IMenuService{
	//菜单栏
	
	@Autowired
	public BaseDAO baseDao;
//权限
	@Override
	public List<MenuVO> getMenu(int power) 
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("power", power);
		List<MenuVO> list = (List<MenuVO>) baseDao.findListBy(MenuVO.class.getName()+".getMenu",paramMap);
		System.out.println(JSON.toJSONString(list));
		return list;
	}

	@Override
	public List<MenuVO> getMenuById(int id, int power)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("power", power);
		paramMap.put("id", id);
		List<MenuVO> list = (List<MenuVO>) baseDao.findListBy(MenuVO.class.getName()+".getMenu",paramMap);
		System.out.println(JSON.toJSONString(list));
		return list;
	}
//parent_id决定是否拥有下拉框
	@Override
	public List<MenuVO> getMenuByParentId(int parent_id, int power)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("power", power);
		paramMap.put("parent_id", parent_id);
		List<MenuVO> list = (List<MenuVO>) baseDao.findListBy(MenuVO.class.getName()+".getMenuByParentId",paramMap);
		System.out.println(JSON.toJSONString(list));
		return list;
	}

	@Override
	public Map<String, Object> updateMenuInfo(UserVO user)
	{
		//更新首页的info_num
		Date date = user.getLast_login_time();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("last_login_time", date);
		//公告栏
		Map<String,Object> map = (Map<String, Object>) baseDao.findOneBy(NewsVO.class.getName()+".getInfoNum", paramMap);
		Map<String,Object> indexParamMap = new HashMap<String,Object>();
		indexParamMap.put("info_num", map.get("num"));
		indexParamMap.put("id", 1);
		 baseDao.update(MenuVO.class.getName()+".updateInfoNum", indexParamMap);
		 
		 
		return null;
	}
	

}


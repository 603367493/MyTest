package com.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.MenuVO;
import com.cm.vo.UserVO;

@Service
public interface IMenuService {
	
	List<MenuVO> getMenu(int power);//х╗оч

	List<MenuVO> getMenuById(int id,int power);
	
	List<MenuVO> getMenuByParentId(int parent_id,int power);
	
	Map<String,Object> updateMenuInfo(UserVO user);
}

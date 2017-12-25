package com.cm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.UserVO;

@Service
public interface IUserService {
//登录
	Map<String,Object> login(String acc,String pwd);
	/**
	 * 刷新登录时间
	 * @param paramMap last_login_time id
	 * @return
	 */
	int updateLoginTime(Map<String,Object> paramMap);
	//更新密码
	Map<String,Object> updatePwd(String pwd,int user_id);
	//获得用户
	List<UserVO> getUserList();
   //获得user_ID
	UserVO getUserById(int user_id);
	//添加用户
	Map<String,Object> add(UserVO userVO);
	//删除用户
	Map<String,Object> deleteUser(UserVO userVO);
	//更新用户
	Map<String,Object> updateUser(UserVO userVO);
	
	Map<String,Object> updatePrice(UserVO userVO);
}


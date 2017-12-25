package com.cm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.UserVO;

@Service
public interface IUserService {
//��¼
	Map<String,Object> login(String acc,String pwd);
	/**
	 * ˢ�µ�¼ʱ��
	 * @param paramMap last_login_time id
	 * @return
	 */
	int updateLoginTime(Map<String,Object> paramMap);
	//��������
	Map<String,Object> updatePwd(String pwd,int user_id);
	//����û�
	List<UserVO> getUserList();
   //���user_ID
	UserVO getUserById(int user_id);
	//����û�
	Map<String,Object> add(UserVO userVO);
	//ɾ���û�
	Map<String,Object> deleteUser(UserVO userVO);
	//�����û�
	Map<String,Object> updateUser(UserVO userVO);
	
	Map<String,Object> updatePrice(UserVO userVO);
}


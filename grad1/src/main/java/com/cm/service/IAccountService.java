package com.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.FamilyVO;

@Service
public interface IAccountService {
	void getAccount(Map<String,Object> paramMap );
	
	/**
	 * 通过user_id获取
	 * @param user_id
	 * @return
	 */
	List<FamilyVO> getFamilyList(int user_id);
	
	
	Map<String,Object> addFamily(FamilyVO familyVO);
	
	Map<String,Object> deleteFamily(FamilyVO familyVO);
	
	FamilyVO getFamilyById(int id);
}

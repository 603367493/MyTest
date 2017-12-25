package com.cm.service;

import java.util.List;
import java.util.Map;

import com.cm.vo.SdjfVO;

public interface ISdjfService {

	List<SdjfVO> getSdjfList(int user_id);
	
	List<SdjfVO> getSdjfList(int user_id,String period);
	
	Map<String,Object> addSdjf(SdjfVO sdjfVO);
	
	Map<String,Object> deleteSdjf(int id);
	
	Map<String,Object> updateSdjf(SdjfVO sdjfVO);
	
	SdjfVO getSdjfById(int id);
}

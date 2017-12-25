package com.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.common.BaseDAO;
import com.cm.service.IDictionaryService;
import com.cm.vo.DictionaryItemVO;
@Service
public class DictionaryItemServiceImpl implements IDictionaryService{

	@Autowired
	public BaseDAO baseDAO;
	
	@Override
	public DictionaryItemVO getContentByCodeAndId(int code, int dictionary_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("code", code);
		paramMap.put("dictionary_id", dictionary_id);
		DictionaryItemVO result = (DictionaryItemVO) baseDAO.findOneBy(DictionaryItemVO.class.getName()+".getContentByCodeAndId", paramMap);
		return result;
	}

	@Override
	public List<DictionaryItemVO> getContentById(int dictionary_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("dictionary_id", dictionary_id);
		List<DictionaryItemVO> result = (List<DictionaryItemVO>) baseDAO.findListBy(DictionaryItemVO.class.getName()+".getContentById", paramMap);
		return result;
	}

}

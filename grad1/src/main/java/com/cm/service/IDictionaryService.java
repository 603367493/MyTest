package com.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cm.vo.DictionaryItemVO;
@Service
public interface IDictionaryService {

	DictionaryItemVO getContentByCodeAndId(int code ,int dictionary_id);
	
	List<DictionaryItemVO> getContentById(int dictionary_id);
}

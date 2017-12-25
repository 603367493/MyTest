package com.cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.service.IDictionaryService;
import com.cm.vo.DictionaryItemVO;
/**
 * ×Öµä
 * @author Administrator
 *
 */
@Controller
@RequestMapping("dicionary")
public class DictionaryController {
	
	@Autowired
	public IDictionaryService iDictionaryService;

	@RequestMapping("getContentById")
	@ResponseBody//·µ»Ødictionary_id
	public List<DictionaryItemVO> getContentById(HttpServletRequest request,HttpServletResponse response,int id){
		return iDictionaryService.getContentById(id);
	}
}

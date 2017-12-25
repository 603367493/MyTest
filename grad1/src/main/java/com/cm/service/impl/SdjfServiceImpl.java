package com.cm.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cm.common.BaseDAO;
import com.cm.contant.DictionaryIdContant;
import com.cm.service.IDictionaryService;
import com.cm.service.INewsService;
import com.cm.service.ISdjfService;
import com.cm.vo.DictionaryItemVO;
import com.cm.vo.FamilyVO;
import com.cm.vo.NewsVO;
import com.cm.vo.SdjfVO;
@Service
public class SdjfServiceImpl implements ISdjfService{

	
	@Autowired
	public BaseDAO baseDAO;
	
	@Autowired
	public IDictionaryService iDictionaryService;

	@Override
	public List<SdjfVO> getSdjfList(int user_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("user_id", user_id);
		List<SdjfVO> resultList = (List<SdjfVO>) baseDAO.findListBy(SdjfVO.class.getName()+".getSdjfList", paramMap);
		for(SdjfVO sdjfVO:resultList){
			DictionaryItemVO dItemVO = iDictionaryService.getContentByCodeAndId(sdjfVO.getState(), DictionaryIdContant.STATE_SDJF);
			sdjfVO.setState_name(dItemVO.getContent());
		}
		return resultList;
	}

	@Override
	public List<SdjfVO> getSdjfList(int user_id, String period) {
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("user_id", user_id);
		paramMap.put("period", period);
		List<SdjfVO> resultList = (List<SdjfVO>) baseDAO.findListBy(SdjfVO.class.getName()+".getSdjfList", paramMap);
		for(SdjfVO sdjfVO:resultList){
			DictionaryItemVO dItemVO = iDictionaryService.getContentByCodeAndId(sdjfVO.getState(), DictionaryIdContant.STATE_SDJF);
			sdjfVO.setState_name(dItemVO.getContent());
		}
		return resultList;
	}

	@Override
	public Map<String, Object> addSdjf(SdjfVO sdjfVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<SdjfVO> list = getSdjfList(sdjfVO.getUser_id(),sf.format(sdjfVO.getPeriod()));
		if(list.size() == 1){
			resultMap.put("flag", true);
			resultMap.put("msg", "该用户在该期间的水电费已经存在，不能操作");
			return resultMap;
		}
		try {
			int i = -1;
			if (sdjfVO.getId() == -1) {

				i = baseDAO.insert(SdjfVO.class.getName() + ".addSdjf", sdjfVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "添加水费成功");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "添加水费失败");
				}

			} else {
				i = baseDAO.update(SdjfVO.class.getName() + ".updateSdjf", sdjfVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "修改水费成功");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "修改水费失败");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "添加水费失败，请联系管理员");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deleteSdjf(int id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		int i = baseDAO.update(SdjfVO.class.getName() + ".deleteSdjf", paramMap);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "删除水费成功");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "删除水费失败");
		}
		return resultMap;
	}

	@Override
	public SdjfVO getSdjfById(int id) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		SdjfVO sdjfVO = (SdjfVO) baseDAO.findOneBy(SdjfVO.class.getName()+".getSdjfById", paramMap);
		System.out.println(JSON.toJSONString(sdjfVO));
		return sdjfVO;
	}

	@Override
	public Map<String, Object> updateSdjf(SdjfVO sdjfVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = baseDAO.update(SdjfVO.class.getName() + ".updateSdjf", sdjfVO);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "缴纳水电费成功");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "缴纳水电费失败");
		}
		return resultMap;
	}
	
	
	


}

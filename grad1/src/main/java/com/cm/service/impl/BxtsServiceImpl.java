package com.cm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.common.BaseDAO;
import com.cm.contant.DictionaryIdContant;
import com.cm.service.IBxtsService;
import com.cm.service.IDictionaryService;
import com.cm.vo.ComplainVO;
import com.cm.vo.DictionaryItemVO;
import com.cm.vo.RepairVO;
import com.sun.org.apache.regexp.internal.RE;
@Service
public class BxtsServiceImpl implements IBxtsService {
	
	@Autowired
	public BaseDAO baseDAO;
	
	@Autowired
	public IDictionaryService iDictionaryService;
	
	@Override
	public Map<String, Object> bx(String content, int user_id, int bx_code,int id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			RepairVO repairVO = new RepairVO();
			repairVO.setContent(content);
			repairVO.setUser_id(user_id);
			repairVO.setType_id(bx_code);
			repairVO.setPublish_time(new Date(System.currentTimeMillis()));
			repairVO.setState("3");
			int i = -1;
			if(id == -1){
				i = baseDAO.insert(RepairVO.class.getName()+".addRepair", repairVO);
			}else{
				repairVO.setId(id);
				i = baseDAO.update(RepairVO.class.getName()+".updateRepair", repairVO);
			}
			 
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "提交成功，请静候相关人员处理");
			}else{
				resultMap.put("flag", true);
				resultMap.put("msg", "提交失败，请联系系统管理员");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "提交失败，请联系系统管理员");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> ts(String content, int user_id, int ts_code,int id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			ComplainVO  complain = new ComplainVO();
			complain.setContent(content);
			complain.setUser_id(user_id);
			complain.setType_id(ts_code);
			complain.setPublish_time(new Date(System.currentTimeMillis()));
			complain.setState("3");
			
			int i = -1;
			if(id == -1){
				i = baseDAO.insert(ComplainVO.class.getName()+".addComplain", complain);
			}else{
				complain.setId(id);
				i = baseDAO.update(ComplainVO.class.getName()+".updateComplain", complain);
			}
			
			//int i = baseDAO.insert(ComplainVO.class.getName()+".addComplain", complain);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "提交成功，请静候相关人员处理");
			}else{
				resultMap.put("flag", true);
				resultMap.put("msg", "提交失败，请联系系统管理员");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "提交失败，请联系系统管理员");
		}
		
		return resultMap;
	}

	@Override
	public List<RepairVO> getRepairList(int user_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();

		paramMap.put("user_id", user_id);
		List<RepairVO> resultList = (List<RepairVO>) baseDAO.findListBy(RepairVO.class.getName()+".getRepairList", paramMap);
		for(RepairVO repairVO :resultList ){
			DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(repairVO.getType_id(), DictionaryIdContant.TYPE_ID_REPAIR);
			DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(repairVO.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
			repairVO.setType_name(dictionaryVOType.getContent());
			repairVO.setState_name(dictionaryVORC.getContent());
		}
		return resultList;
	}

	@Override
	public Map<String, Object> scbx(int user_id, int bx_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			paramMap.put("user_id", user_id);
			paramMap.put("bx_id",bx_id);
			int i = baseDAO.update(RepairVO.class.getName()+".deleteRepair", paramMap);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "撤销成功");
			}else{
				resultMap.put("flag", true);
				resultMap.put("msg", "提交失败，请联系系统管理员");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "提交失败，请联系系统管理员");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> scts(int user_id, int sc_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairVO getRepairById(int id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		RepairVO repairVO = (RepairVO) baseDAO.findOneBy(RepairVO.class.getName()+".getRepairById", paramMap);
		DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(repairVO.getType_id(), DictionaryIdContant.TYPE_ID_REPAIR);
		DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(repairVO.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
		repairVO.setType_name(dictionaryVOType.getContent());
		repairVO.setState_name(dictionaryVORC.getContent());
		return repairVO;
	}

	@Override
	public List<ComplainVO> getComplainList(int user_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_id", user_id);
		List<ComplainVO> resultList = (List<ComplainVO>) baseDAO.findListBy(ComplainVO.class.getName()+".getComplainList", paramMap);
		for(ComplainVO cVO:resultList){
			DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(cVO.getType_id(), DictionaryIdContant.TYPE_ID_COMPLAIN);
			DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(cVO.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
			cVO.setState_name(dictionaryVORC.getContent());
			cVO.setType_name(dictionaryVOType.getContent());
		}
		return resultList;
	}

	@Override
	public ComplainVO getComplainById(int id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		ComplainVO complainVO = (ComplainVO) baseDAO.findOneBy(ComplainVO.class.getName()+".getComplainById", paramMap);
		DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(complainVO.getType_id(), DictionaryIdContant.TYPE_ID_COMPLAIN);
		DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(complainVO.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
		complainVO.setType_name(dictionaryVOType.getContent());
		complainVO.setState_name(dictionaryVORC.getContent());
		return complainVO;
	}

	@Override
	public List<RepairVO> getAllRepair(int state) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//state = -1获取所有
		paramMap.put("state", state);
		
		List<RepairVO> resultList = (List<RepairVO>) baseDAO.findListBy(RepairVO.class.getName()+".getAllRepair", paramMap);
		for(RepairVO repairVO :resultList ){
			DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(repairVO.getType_id(), DictionaryIdContant.TYPE_ID_REPAIR);
			DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(repairVO.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
			repairVO.setType_name(dictionaryVOType.getContent());
			repairVO.setState_name(dictionaryVORC.getContent());
		}
		return resultList;
	}

	@Override
	public List<ComplainVO> getAllComplain(int state) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//state = -1获取所有
		paramMap.put("state", state);
		
		List<ComplainVO> resultList = (List<ComplainVO>) baseDAO.findListBy(ComplainVO.class.getName()+".getAllComplain", paramMap);
		for(ComplainVO c :resultList ){
			DictionaryItemVO dictionaryVOType = iDictionaryService.getContentByCodeAndId(c.getType_id(), DictionaryIdContant.TYPE_ID_COMPLAIN);
			DictionaryItemVO dictionaryVORC = iDictionaryService.getContentByCodeAndId(Integer.parseInt(c.getState()), DictionaryIdContant.STATE_REPAIR_COMPLAIN);
			c.setType_name(dictionaryVOType.getContent());
			c.setState_name(dictionaryVORC.getContent());
		}
		return resultList;
	}

	@Override
	public Map<String, Object> clRepair(RepairVO repairVO) {

		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int i = baseDAO.update(RepairVO.class.getName()+".updateRepair", repairVO);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "处理成功");
			}else{
				resultMap.put("flag", false);
				resultMap.put("msg", "处理失败");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "处理失败，请联系系统管理员");
		}
		
		return resultMap;
		
	}

	@Override
	public Map<String, Object> clComplain(ComplainVO complainVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int i = baseDAO.update(ComplainVO.class.getName()+".updateComplain", complainVO);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "处理成功");
			}else{
				resultMap.put("flag",false);
				resultMap.put("msg", "处理失败");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "处理失败，请联系系统管理员");
		}		
		return resultMap;
	}

	@Override
	public Map<String, Object> fkRepair(RepairVO repairVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int i = baseDAO.update(RepairVO.class.getName()+".updateRepair", repairVO);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "反馈成功");
			}else{
				resultMap.put("flag", true);
				resultMap.put("msg", "反馈失败");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "反馈失败，请联系系统管理员");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> fkComplain(ComplainVO complainVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int i = baseDAO.update(ComplainVO.class.getName()+".updateComplain", complainVO);
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "反馈成功");
			}else{
				resultMap.put("flag", true);
				resultMap.put("msg", "反馈失败");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", true);
			resultMap.put("msg", "反馈失败，请联系系统管理员");
		}
		
		return resultMap;
	}

}

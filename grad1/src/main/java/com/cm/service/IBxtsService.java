package com.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.ComplainVO;
import com.cm.vo.RepairVO;
@Service
public interface IBxtsService {
	
	

	List<RepairVO> getRepairList(int user_id);
	
	Map<String,Object> bx(String content ,int user_id,int bx_code,int id);
	
	Map<String,Object> ts(String content,int user_id,int ts_code,int id);
	
	Map<String,Object> scbx(int user_id,int bx_id);
	
	Map<String,Object> scts(int user_id,int sc_id);
	
	RepairVO getRepairById(int id);
	
	List<ComplainVO> getComplainList(int user_id);
	
	ComplainVO getComplainById(int id);
	
	
	List<RepairVO> getAllRepair(int state);
	
	List<ComplainVO> getAllComplain(int state);
	/**
	 * 处理保修
	 * @param repairVO
	 * @return
	 */
	Map<String,Object> clRepair(RepairVO repairVO);
	/**
	 * 处理投诉
	 * @param complainVO
	 * @return
	 */
	Map<String,Object> clComplain(ComplainVO complainVO);
	/**
	 * 反馈保修
	 * @param repairVO
	 * @return
	 */
	Map<String,Object> fkRepair(RepairVO repairVO);
	/**
	 * 反馈投诉
	 * @param complainVO
	 * @return
	 */
	Map<String,Object> fkComplain(ComplainVO complainVO);
}

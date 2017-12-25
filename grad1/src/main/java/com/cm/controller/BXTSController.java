package com.cm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.service.IBxtsService;
import com.cm.vo.ComplainVO;
import com.cm.vo.RepairVO;
import com.cm.vo.UserVO;

/**
 * ����Ͷ��Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="bxts")
public class BXTSController {
	
	@Autowired
	public IBxtsService iBxtsService;//ʵ����

	@RequestMapping("toRepairUI")//����ά��ҳ��
	public String toRepairUI(HttpServletRequest request){
		return "repair";
	}
	
	@RequestMapping("toComplainUI")//����Ͷ��ҳ��
	public String toComplainUI(HttpServletRequest request){
		return "complain";
	}
	/**
	 * ����
	 * @return
	 */
	@RequestMapping("bx")
	@ResponseBody//���ñ���
	public Map<String,Object> bx(HttpServletRequest request,String content,int bx_code,int id){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iBxtsService.bx(content, user.getId(), bx_code,id);
	}
	
	@RequestMapping("ts")
	@ResponseBody //����Ͷ��
	public Map<String,Object> ts(HttpServletRequest request,String content,int ts_code,int id){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iBxtsService.ts(content, user.getId(), ts_code,id);
	}
	
	@RequestMapping("getRepairList")
	@ResponseBody//���ID
	public List<RepairVO> getRepairList(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iBxtsService.getRepairList(user.getId());
	}

	
	@RequestMapping("toAddRepairUI")//������ά��ҳ��
	public String toAddRepairUI(HttpServletRequest request,
			@RequestParam(required=false)String isView,
			@RequestParam(required=false)Integer id){
		if(isView.equals("true")){
			RepairVO repairVO = iBxtsService.getRepairById(id);
			request.setAttribute("repairVO", repairVO);
		}
		return "addRepair";
	}
	
	@RequestMapping("getComplainList")
	@ResponseBody//���ID
	public List<ComplainVO> getComplainList(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iBxtsService.getComplainList(user.getId());
	}
	
	@RequestMapping("toAddComplainUI")//������Ͷ��ҳ��
	public String toAddComplainUI(HttpServletRequest request,
			@RequestParam(required=false)String isView,
			@RequestParam(required=false)Integer id){
		if(isView.equals("true")){
			ComplainVO complainVO = iBxtsService.getComplainById(id);
			request.setAttribute("complainVO", complainVO);
		}
		return "addComplain";
	}
	
	@RequestMapping("toComplainManagerUI.do")//���ع���Ա����Ͷ��ҳ��
	public String toComplainManagerUI(HttpServletRequest request){
		return "complainManager";
	}
	
	@RequestMapping("toRepairManagerUI.do")//���ع���ά��ҳ��
	public String toRepairManagerUI(HttpServletRequest request){
		return "repairManager";
	}
	
	@RequestMapping("getAllRepair")
	@ResponseBody//�������
	public List<RepairVO> getAllRepair(HttpServletRequest request,Integer state){
		return iBxtsService.getAllRepair(state);
	}
	
	@RequestMapping("clRepair")
	@ResponseBody//�������
	public Map<String,Object> clRepair(HttpServletRequest request,int id){
		RepairVO repairVO = new RepairVO();
		repairVO.setId(id);
		repairVO.setState("2");
		return iBxtsService.clRepair(repairVO);
	}
	
	@RequestMapping("fkRepair")
	@ResponseBody//�ص�����
	public Map<String,Object> fkRepair(HttpServletRequest request,int id,String content){
		RepairVO repairVO = new RepairVO();
		repairVO.setId(id);
		repairVO.setState("1");
		repairVO.setFeedback(content);
		repairVO.setFeedback_time(new Date(System.currentTimeMillis()));
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		repairVO.setFeedback_user_id(user.getId());
		return iBxtsService.fkRepair(repairVO);
	}
	
	@RequestMapping("getAllComplain")
	@ResponseBody//�������Ͷ������
	public List<ComplainVO> getAllComplain(HttpServletRequest request,Integer state){
		return iBxtsService.getAllComplain(state);
	}
	
	@RequestMapping("clComplain")
	@ResponseBody//���
	public Map<String,Object> clComplain(HttpServletRequest request,int id){
		ComplainVO complainVO = new ComplainVO();
		complainVO.setId(id);
		complainVO.setState("2");
		return iBxtsService.clComplain(complainVO);
	}
	
	@RequestMapping("fkComplain")
	@ResponseBody//�ص�
	public Map<String,Object> fkComplain(HttpServletRequest request,int id,String content){
		ComplainVO complainVO = new ComplainVO();
		complainVO.setId(id);
		complainVO.setState("1");
		complainVO.setFeedback(content);
		complainVO.setFeedback_time(new Date(System.currentTimeMillis()));
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		complainVO.setFeedback_user_id(user.getId());
		return iBxtsService.fkComplain(complainVO);
	}
	
}

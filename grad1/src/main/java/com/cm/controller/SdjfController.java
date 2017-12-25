package com.cm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.service.ISdjfService;
import com.cm.service.IUserService;
import com.cm.vo.SdjfVO;
import com.cm.vo.UserVO;

/**
 * 水电缴费Controller
 * @author
 *
 */
@Controller
@RequestMapping("sdjf")
public class SdjfController {
	
	@Autowired
	public ISdjfService iSdjfService;
	
	@Autowired
	public IUserService iUserService;

	@RequestMapping("toSdjfUI.do")
	public String toSdjfUI(HttpServletRequest request){
		return "sdjf";
	}
	
	@RequestMapping("getSdjfList.do")
	@ResponseBody
	public List<SdjfVO> getSdjfList(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iSdjfService.getSdjfList(user.getId());
	}
	
	@RequestMapping("toSdjfManagerUI.do")
	public String toSdjfManagerUI(HttpServletRequest request){
		return "sdjfManager";
	}
	
	@RequestMapping("getSdjfListByUserId.do")
	@ResponseBody
	public List<SdjfVO> getSdjfListByUserId(HttpServletRequest request,int user_id,String period){
		System.out.println("获取水电费列表传入参数：user_id"+user_id+"period:"+period);
		return iSdjfService.getSdjfList(user_id,period);
	}
	//添加水电费
	@RequestMapping("toAddSdjfUI.do")
	public String toAddSdjfUI(HttpServletRequest request,int flag,
			@RequestParam(required=false)Integer id){
		if(flag != 1){
			SdjfVO  sdjfVO = iSdjfService.getSdjfById(id);
			request.setAttribute("sdjfVO", sdjfVO);
			
		}
		request.setAttribute("flag", flag);
		return "addSdjf";
	}
	
//删除水电费
@RequestMapping(value="deleteSdjf")
@ResponseBody
public Map<String,Object>deleteSdjf(int id){
	SdjfVO sdjfVO = new SdjfVO();
	sdjfVO.setId(id);
	sdjfVO.setDelete_flag(1);
	return iSdjfService.deleteSdjf(id);
			}
	
	
	@RequestMapping(value="addSdjf",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addSdjf(HttpServletRequest request,@RequestBody SdjfVO sdjfVO){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		sdjfVO.setPublish_id(user.getId());
		sdjfVO.setPublish_time(new Date(System.currentTimeMillis()));
		sdjfVO.setPublish_name(user.getName());
		sdjfVO.setDelete_flag(0);
		sdjfVO.setState(2);
		return iSdjfService.addSdjf(sdjfVO);
	}
	
	@RequestMapping(value="jn")
	@ResponseBody
	public Map<String,Object> jn(HttpServletRequest request,int id,Double allPrice){
		
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		UserVO userVo = iUserService.getUserById(user.getId());
		Map<String,Object> resultMap = new HashMap<String,Object>();

		if(userVo!=null){
			if(userVo.getPrice()<allPrice){
				resultMap.put("flag", false);
				resultMap.put("msg", "账号余额不足，缴纳水电费失败");
				return resultMap;
			}
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "系统出错，请联系管理员");
			return resultMap;
		}
		
		SdjfVO sdjfVO = new SdjfVO();
		sdjfVO.setId(id);
		sdjfVO.setPublish_id(user.getId());
		sdjfVO.setPublish_time(new Date(System.currentTimeMillis()));
		sdjfVO.setJf_time(new Date(System.currentTimeMillis()));
		sdjfVO.setPublish_name(user.getName());
		sdjfVO.setState(1);
		return iSdjfService.updateSdjf(sdjfVO);
	}
}

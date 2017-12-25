package com.cm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cm.service.ILicenseService;
import com.cm.vo.FamilyVO;
import com.cm.vo.LicenseVO;
import com.cm.vo.UserVO;
/**
 * ����Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/license")
public class LicenseController {
	
	@Autowired
	public ILicenseService iLicenseService;

	@RequestMapping("getLicenseList")
	@ResponseBody//���
	public List<LicenseVO> getLicenseList(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iLicenseService.getLicenseList(user.getId());
	};
	
	@RequestMapping("/toAddLicenseUI.do")//����ҳ��
	public String toAddLicenseUI(HttpServletRequest request,int user_id){
		request.setAttribute("user_id", user_id);
		return "addLicense";
	}
	
	@RequestMapping(value="addLicense",method=RequestMethod.POST)
	@ResponseBody//������ֵ
	public Map<String,Object> addLicense(@RequestBody LicenseVO licenseVO){
		System.out.println(JSON.toJSONString(licenseVO));
		return iLicenseService.addLicense(licenseVO);
	}
	

	@RequestMapping(value="deleteLicense")
	@ResponseBody//ɾ��
	public Map<String,Object> deleteLicense(int id){
		return iLicenseService.deleteLicense(id);
	}
	
}

package com.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cm.common.BaseDAO;
import com.cm.contant.DictionaryIdContant;
import com.cm.service.IAccountService;
import com.cm.service.ICarLocationService;
import com.cm.service.IDictionaryService;
import com.cm.service.ILicenseService;
import com.cm.vo.DictionaryItemVO;
import com.cm.vo.FamilyVO;
import com.cm.vo.LicenseVO;
@Service
public class LicenseServiceImpl implements ILicenseService{
	
	
	@Autowired
	public BaseDAO baseDAO;
	
	@Autowired
	public IDictionaryService iDictionaryService;
	

	@Override
	public List<LicenseVO> getLicenseList(int user_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		paramMap.put("user_id", user_id);
		List<LicenseVO> resultList = (List<LicenseVO>) baseDAO.findListBy(LicenseVO.class.getName()+".getLicenseList", paramMap);
		return resultList;
	}

	@Override
	public Map<String, Object> addLicense(LicenseVO licenseVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		try {
			int i = -1;
			if (licenseVO.getId() == -1) {

				i = baseDAO.insert(LicenseVO.class.getName() + ".addLicense", licenseVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "��ӳ��Ƴɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "��ӳ���ʧ��");
				}

			} else {
				i = baseDAO.update(LicenseVO.class.getName() + ".updateLicense", licenseVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "�޸ĳ��Ƴɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "�޸ĳ���ʧ��");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "��ӳ���ʧ�ܣ�����ϵ����Ա");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deleteLicense(int id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		Map<String,Object> paramMap = new HashMap<String,Object>();	
		try{
			paramMap.put("id",id);
			int i = baseDAO.update(LicenseVO.class.getName()+".deleteLicense",paramMap);
			if (i > 0) {
				resultMap.put("flag", true);
				resultMap.put("msg", "�޸ĳ��Ƴɹ�");
			} else {
				resultMap.put("flag", false);
				resultMap.put("msg", "�޸ĳ���ʧ��");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "��ӳ���ʧ�ܣ�����ϵ����Ա");
		}
		
		return resultMap;
	}

}

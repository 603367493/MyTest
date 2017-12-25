package com.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.FamilyVO;
import com.cm.vo.LicenseVO;

@Service
public interface ILicenseService {
	List<LicenseVO> getLicenseList(int user_id);
	
	Map<String,Object> addLicense(LicenseVO licenseVO);
	
	Map<String,Object> deleteLicense(int id);
}

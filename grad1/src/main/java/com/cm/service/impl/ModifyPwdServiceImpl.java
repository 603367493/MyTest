package com.cm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.common.BaseDAO;
import com.cm.service.IModifyPwdService;
@Service
public class ModifyPwdServiceImpl implements IModifyPwdService {
	
	@Autowired
	public BaseDAO BaseDAO;

	@Override
	public Map<String, Object> modifyPwd(String pwd, String userId) {
		return null;
	}

}

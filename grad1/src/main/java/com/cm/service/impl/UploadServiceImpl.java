package com.cm.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cm.common.BaseDAO;
import com.cm.service.IUploadService;
import com.cm.vo.UserVO;

public class UploadServiceImpl implements IUploadService {
 
	@Autowired
	public BaseDAO baseDao;
	@Override
	public String upload(UserVO user, MultipartFile uploadFile, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}

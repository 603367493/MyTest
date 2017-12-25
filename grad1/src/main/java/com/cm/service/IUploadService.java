package com.cm.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cm.vo.UserVO;

public interface IUploadService {
	 String upload(UserVO user,MultipartFile uploadFile,HttpServletRequest request); 
}

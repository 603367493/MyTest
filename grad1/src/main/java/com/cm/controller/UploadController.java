package com.cm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cm.service.IUserService;
import com.cm.vo.UserVO;
@Controller
@RequestMapping(value="/upload/")
public class UploadController 
{ 
	
	@Autowired
	private IUserService  userService;

	@RequestMapping("toUploadUI")
	public String toUploadUI(){
		return "testUpload2";
	}
	
	  @RequestMapping(value="upload",method=RequestMethod.POST)
	public String upload(HttpSession session,MultipartFile uploadFile,HttpServletRequest request){
//			��ȡ�ϴ��ļ����ļ���
			String filename= uploadFile.getOriginalFilename();
//			��ȡ�ϴ��ļ���Ŀ���ļ��е�·��
//			String toPath2 = request.getServletContext().getRealPath("/upload/");
//			�ж�Ŀ���ļ����Ƿ���ڣ��������򴴽�
			UserVO user=(UserVO)session.getAttribute("user");
			String toPath="D:/��Ŀ/1.4/grad/webapp/img/";
			
			File toFolder = new File(toPath);
			if(!toFolder.exists()){
				toFolder.mkdir();
			}
//			����Ŀ���ļ�
			File toFile = new File(toPath,filename);
//			��Դ�ļ�д��Ŀ���ļ�
			try {
				uploadFile.transferTo(toFile);//�����д��Ŀ���ļ�
			} catch (IllegalStateException | IOException e) {
				System.out.println("�ϴ��ļ�ʧ�ܣ�");
				e.printStackTrace();
			}
			
			user.setImage_url(toPath+filename);
//			userService.add(user);
			
			request.setAttribute("path", user.getImage_url());
			return "uploadSuccess";
		}
		
  }


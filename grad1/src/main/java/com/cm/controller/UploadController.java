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
//			获取上传文件的文件名
			String filename= uploadFile.getOriginalFilename();
//			获取上传文件的目标文件夹的路径
//			String toPath2 = request.getServletContext().getRealPath("/upload/");
//			判断目标文件夹是否存在，不存在则创建
			UserVO user=(UserVO)session.getAttribute("user");
			String toPath="D:/项目/1.4/grad/webapp/img/";
			
			File toFolder = new File(toPath);
			if(!toFolder.exists()){
				toFolder.mkdir();
			}
//			创建目标文件
			File toFile = new File(toPath,filename);
//			将源文件写到目标文件
			try {
				uploadFile.transferTo(toFile);//这个是写到目标文件
			} catch (IllegalStateException | IOException e) {
				System.out.println("上传文件失败！");
				e.printStackTrace();
			}
			
			user.setImage_url(toPath+filename);
//			userService.add(user);
			
			request.setAttribute("path", user.getImage_url());
			return "uploadSuccess";
		}
		
  }


package com.cm.controller;

import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.cm.service.INewsService;
import com.cm.vo.FamilyVO;
import com.cm.vo.NewsVO;
import com.cm.vo.UserVO;
import com.sun.org.apache.regexp.internal.recompile;
/**
 * 新闻
 * @author Administrator
 *
 */
@Controller
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	public INewsService iNewsService;
//获得所有新闻
	@RequestMapping(value="getNews")
	@ResponseBody
	public List<NewsVO> getNews(){
		return iNewsService.getAllNews();
	}
	
	@RequestMapping(value="getHSZNews")
	@ResponseBody
	public List<NewsVO> getHSZNews(){
		return iNewsService.getHSZNews();
	}	
	
	/**
	 * 
	 * @param request
	 * @param isHSZ 如果等于0  就是查看公告栏信息  等于1就是查看回收站信息
	 * @return
	 */
	@RequestMapping("toNewManagerUI")
	public String toNewManagerUI(HttpServletRequest request,Integer isHSZ){
		request.setAttribute("isHSZ", isHSZ);
		return "newsManager";
	}
	
	@RequestMapping("toAddNewUI")//设置newvo以及他的flag
	public String toAddNewUI(HttpServletRequest request,Integer flag,
			@RequestParam(required=false)Integer id){
		if(flag != 1){
			NewsVO newVO = iNewsService.getNewsById(id);
			request.setAttribute("newVO", newVO);
		}
		
		request.setAttribute("flag", flag);
		return "addNews";
	}
	
	@RequestMapping(value="addNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addNews(HttpServletRequest request,@RequestBody NewsVO newsVO){
		System.out.println(JSON.toJSONString(newsVO));
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		newsVO.setDelete_flag(0);
		
		if(newsVO.getId() != -1 ){
			newsVO.setModify_name(user.getName());
			newsVO.setModify_time(new Date(System.currentTimeMillis()));
		}else{
			newsVO.setPublish_time(new Date(System.currentTimeMillis()));
			newsVO.setPublish_name(user.getName());
		}
		return iNewsService.addNews(newsVO);
	}
	
	@RequestMapping(value="deleteNews")
	@ResponseBody
	public Map<String,Object> deleteNews(HttpServletRequest request,int id){
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		NewsVO newVO = new NewsVO();
		newVO.setDelete_flag(1);
		newVO.setId(id);
		newVO.setModify_name(user.getName());
		newVO.setModify_time(new Date(System.currentTimeMillis()));
		return iNewsService.deleteNews(newVO);
	}
	

	@RequestMapping(value="unDeleteNews")
	@ResponseBody
	public Map<String,Object> unDeleteNews(HttpServletRequest request,int id){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		NewsVO newVO = new NewsVO();
		newVO.setDelete_flag(0);
		newVO.setId(id);
		newVO.setModify_name(user.getName());
		newVO.setModify_time(new Date(System.currentTimeMillis()));
		return iNewsService.deleteNews(newVO);
	}
}

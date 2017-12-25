package com.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.NewsVO;
@Service
public interface INewsService {
	
	List<NewsVO> getAllNews();
	
	List<NewsVO> getHSZNews();
	
	Map<String,Object> addNews(NewsVO newsVO);
	
	Map<String,Object> deleteNews(NewsVO newsVO);
	
	NewsVO getNewsById(int id);
	
	Map<String,Object> unDeleteNews(NewsVO newsVO);
}

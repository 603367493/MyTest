package com.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.common.BaseDAO;
import com.cm.service.INewsService;
import com.cm.vo.FamilyVO;
import com.cm.vo.NewsVO;
@Service
public class NewsServiceImpl implements INewsService{

	
	@Autowired
	public BaseDAO baseDAO;
	
	@Override
	public List<NewsVO> getAllNews() {
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();	
			List<NewsVO> list = (List<NewsVO>) baseDAO.findListBy(NewsVO.class.getName()+".getAllNews", paramMap);
			return list;
		}catch(Exception e){
			System.out.println("获取公告数据失败:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> addNews(NewsVO newsVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int i = -1;
			if (newsVO.getId() == -1) {

				i = baseDAO.insert(NewsVO.class.getName() + ".addNews", newsVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "添加公告成功");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "添加公告失败");
				}

			} else {
				i = baseDAO.update(NewsVO.class.getName() + ".updateNews", newsVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "修改公告成功");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "修改公告失败");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "添加公告失败，请联系管理员");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deleteNews(NewsVO newsVO) {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = baseDAO.update(NewsVO.class.getName() + ".updateNews",newsVO);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "移进回收站成功");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "移进回收站失败");
		}
		return resultMap;
	}

	@Override
	public NewsVO getNewsById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		NewsVO newVO = (NewsVO) baseDAO.findOneBy(NewsVO.class.getName() + ".getNewsById",paramMap);

		return newVO;
	}

	@Override
	public Map<String, Object> unDeleteNews(NewsVO newsVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = baseDAO.update(NewsVO.class.getName() + ".updateNews",newsVO);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "移出回收站成功");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "移出回收站失败");
		}
		return resultMap;
	}

	@Override
	public List<NewsVO> getHSZNews() {
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();	
			List<NewsVO> list = (List<NewsVO>) baseDAO.findListBy(NewsVO.class.getName()+".getHSZNews", paramMap);
			return list;
		}catch(Exception e){
			System.out.println("获取公告数据失败:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}

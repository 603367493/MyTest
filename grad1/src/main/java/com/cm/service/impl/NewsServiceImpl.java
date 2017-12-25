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
			System.out.println("��ȡ��������ʧ��:"+e.getMessage());
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
					resultMap.put("msg", "��ӹ���ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "��ӹ���ʧ��");
				}

			} else {
				i = baseDAO.update(NewsVO.class.getName() + ".updateNews", newsVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "�޸Ĺ���ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "�޸Ĺ���ʧ��");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "��ӹ���ʧ�ܣ�����ϵ����Ա");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deleteNews(NewsVO newsVO) {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = baseDAO.update(NewsVO.class.getName() + ".updateNews",newsVO);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "�ƽ�����վ�ɹ�");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "�ƽ�����վʧ��");
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
			resultMap.put("msg", "�Ƴ�����վ�ɹ�");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "�Ƴ�����վʧ��");
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
			System.out.println("��ȡ��������ʧ��:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}

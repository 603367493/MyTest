package com.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cm.common.BaseDAO;
import com.cm.contant.DictionaryIdContant;
import com.cm.service.IAccountService;
import com.cm.service.IDictionaryService;
import com.cm.vo.DictionaryItemVO;
import com.cm.vo.FamilyVO;
import com.cm.vo.UserVO;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	public BaseDAO baseDAO;

	@Autowired
	public IDictionaryService iDictionaryService;

	@Override
	public void getAccount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub

	}
//��ü�����Ϣ
	@Override
	public List<FamilyVO> getFamilyList(int user_id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		List<FamilyVO> list = (List<FamilyVO>) baseDAO.findListBy(FamilyVO.class.getName() + ".getFamilyList",
				paramMap);
		for (FamilyVO f : list) {
			System.out.println(JSON.toJSONString(f));
			DictionaryItemVO dItemVO = iDictionaryService.getContentByCodeAndId(f.getRelation_id(),
					DictionaryIdContant.RELATION);
			f.setRelation_name(dItemVO.getContent());
		}
		return list;
	}

	@Override
	public Map<String, Object> addFamily(FamilyVO familyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int i = -1;
			if (familyVO.getId() == -1) {

				i = baseDAO.insert(FamilyVO.class.getName() + ".addFamily", familyVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "��Ӽ�ͥ��Ա�ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "��Ӽ�ͥ��Աʧ��");
				}

			} else {
				i = baseDAO.update(FamilyVO.class.getName() + ".updateFamily", familyVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "�޸ļ�ͥ��Ա�ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "�޸ļ�ͥ��Աʧ��");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "����û�ʧ�ܣ�����ϵ����Ա");
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> deleteFamily(FamilyVO familyVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = baseDAO.update(FamilyVO.class.getName() + ".updateFamily", familyVO);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "ɾ����ͥ��Ա�ɹ�");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "ɾ����ͥ��Աʧ��");
		}
		return resultMap;
	}

	@Override
	public FamilyVO getFamilyById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		FamilyVO familyVO = (FamilyVO) baseDAO.findOneBy(FamilyVO.class.getName()+".getFamilyById", paramMap);
		DictionaryItemVO dItemVO = iDictionaryService.getContentByCodeAndId(familyVO.getRelation_id(),
						DictionaryIdContant.RELATION);
		familyVO.setRelation_name(dItemVO.getContent());
		return familyVO ;
	}

}

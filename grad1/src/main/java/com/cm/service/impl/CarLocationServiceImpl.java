package com.cm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cm.common.BaseDAO;
import com.cm.common.DoubleUtils;
import com.cm.contant.DictionaryIdContant;
import com.cm.service.IAccountService;
import com.cm.service.ICarLocationService;
import com.cm.service.IDictionaryService;
import com.cm.service.IUserService;
import com.cm.vo.CarVO;
import com.cm.vo.DictionaryItemVO;
import com.cm.vo.FamilyVO;
import com.cm.vo.UserVO;
@Service
@Transactional
public class CarLocationServiceImpl implements ICarLocationService{
	
	
	@Autowired
	public BaseDAO baseDAO;
	
	@Autowired
	public IDictionaryService iDictionaryService;
	
	@Autowired
	public IUserService iUserService;

	@Override
	public List<CarVO> getCarLocationLists() {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("state", 2);
		List<CarVO> resultList= (List<CarVO>) baseDAO.findListBy(CarVO.class.getName()+".getCarLocationLists", paramMap);
		for(CarVO carVO : resultList){
			DictionaryItemVO diVO = iDictionaryService.getContentByCodeAndId(carVO.getState(),DictionaryIdContant.CAR_LOCATION_STATE);
			carVO.setState_name(diVO.getContent());
		}
		return resultList;
	}
//����
	@Override//�������ҳ�������
	public Map<String, Object> buy(int jfCode, int carId,int userId,int licenseId,int num,double allPrice) {
		
		Map<String,Object> paramMap = new HashMap<>();
		Map<String,Object> resultMap = new HashMap<>();
		try{
			
			//�ж�����Ƿ��㹻
			UserVO userVO= iUserService.getUserById(userId);
			double price = userVO.getPrice();
			if(allPrice > price){
				resultMap.put("flag", false);
				resultMap.put("msg", "����ʧ�ܣ��˻�����");
				return resultMap;
			}
			
			
			
			paramMap.put("pay_id", jfCode);
			paramMap.put("car_id", carId);
			paramMap.put("licenseId", licenseId);
			long now = System.currentTimeMillis();
			Date date = new Date(now);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(jfCode == 1){
				calendar.add(Calendar.DATE, num);	//��һ��		
			}else if(jfCode == 2){
				calendar.add(Calendar.MONTH, num);		//��һ��
			}else{
				calendar.add(Calendar.YEAR, num);	//��һ��
			}
			paramMap.put("user_id", userId);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
			paramMap.put("userable_time", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
			int i = baseDAO.update(CarVO.class.getName()+".buy", paramMap);
			if(i > 0){
				UserVO userVo1 = new UserVO();
				double finallyPrice = DoubleUtils.sub(price, allPrice).doubleValue();
				userVo1.setPrice(finallyPrice);
				userVo1.setId(userId);
				Map<String,Object> updateUserMap  = iUserService.updateUser(userVo1);
				
				if(updateUserMap.get("flag").toString().equals("true")){
					resultMap.put("flag", true);
					resultMap.put("finallyPrice", finallyPrice);
					resultMap.put("msg", "����ɹ�");
				}else{
					resultMap.put("flag", false);
					resultMap.put("msg", "����ʧ��");
				}
				
				
			}else{
				resultMap.put("flag", false);
				resultMap.put("msg", "����ʧ��");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "����ʧ��");
		}
		return resultMap;
	}

	@Override
	public List<CarVO> getYJFInfo(int userId) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		List<CarVO> list = (List<CarVO>) baseDAO.findListBy(CarVO.class.getName()+".getYJFInfo", paramMap);
		System.out.println(JSON.toJSONString(list));
		return list;
	}

	@Override
	public Map<String, Object> xf(int car_location_id, int user_id,Date userable_time,int jfCode,int num,double allPrice) {
		Map<String,Object> paramMap = new HashMap<>();
		Map<String,Object> resultMap = new HashMap<>();
		try{
			
			//�ж�����Ƿ��㹻
			UserVO userVO= iUserService.getUserById(user_id);
			double price = userVO.getPrice();
			if(allPrice > price){
				resultMap.put("flag", false);
				resultMap.put("msg", "����ʧ�ܣ��˻�����");
				return resultMap;
			}
			
			
			
			paramMap.put("car_location_id", car_location_id);
			paramMap.put("user_id", user_id);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(userable_time);
			if(jfCode == 1){
				calendar.add(Calendar.DAY_OF_MONTH, num);			
			}else if(jfCode == 2){
				calendar.add(Calendar.MONTH, num);		
			}else{
				calendar.add(Calendar.YEAR, num);	
			}
			System.out.println(calendar);
			paramMap.put("userable_time", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
			int i = baseDAO.update(CarVO.class.getName()+".updateUserableTime", paramMap);
			if(i > 0){
				
				
				UserVO userVo1 = new UserVO();
				double finallyPrice = DoubleUtils.sub(price, allPrice).doubleValue();
				userVo1.setPrice(finallyPrice);
				userVo1.setId(user_id);
				Map<String,Object> updateUserMap  = iUserService.updateUser(userVo1);
				
				if(updateUserMap.get("flag").toString().equals("true")){
					resultMap.put("flag", true);
					resultMap.put("finallyPrice", finallyPrice);
					resultMap.put("msg", "���ѳɹ�");
				}else{
					resultMap.put("flag", false);
					resultMap.put("msg", "����ʧ��");
				}
				
				
				/*resultMap.put("flag", true);
				resultMap.put("msg", "���ѳɹ�");*/
			}else{
				resultMap.put("flag", false);
				resultMap.put("msg", "����ʧ��");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "����ʧ��");
		}
		
		return resultMap;
	}

	@Override
	public List<CarVO> getAllCarLocationLists() {
		Map<String,Object> paramMap = new HashMap<>();
		List<CarVO> resultList= (List<CarVO>) baseDAO.findListBy(CarVO.class.getName()+".getCarLocationLists", paramMap);
		for(CarVO carVO : resultList){
			DictionaryItemVO diVO = iDictionaryService.getContentByCodeAndId(carVO.getState(),DictionaryIdContant.CAR_LOCATION_STATE);
			carVO.setState_name(diVO.getContent());
		}
		return resultList;
	}

	@Override
	public Map<String, Object> addCar(CarVO carVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//����no��ȡ��carVO,���carVO��Ϊnull,�򷵻���ʾ�������
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("no", carVO.getNo());
		CarVO cVO = (CarVO) baseDAO.findOneBy(CarVO.class.getName()+".getCarById", paramMap);
		
		try {
			int i = -1;
			if (carVO.getId() == -1) {
				if(cVO != null){
					resultMap.put("flag", false);
					resultMap.put("msg", "��λ����Ѵ���");
					return resultMap;
				}
				i = baseDAO.insert(CarVO.class.getName() + ".addCar", carVO);//���복λ
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "��ӳ�λ�ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "��ӳ�λʧ��");
				}

			} else {
				i = baseDAO.update(CarVO.class.getName() + ".updateCar", carVO);
				if (i > 0) {
					resultMap.put("flag", true);
					resultMap.put("msg", "�޸ĳ�λ�ɹ�");
				} else {
					resultMap.put("flag", false);
					resultMap.put("msg", "�޸ĳ�λʧ��");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "��ӳ�λʧ�ܣ�����ϵ����Ա");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> deleteCar(int id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		int i = baseDAO.update(CarVO.class.getName() + ".deleteCar", paramMap);//ɾ����λ
		System.out.println(i);
		if (i > 0) {
			resultMap.put("flag", true);
			resultMap.put("msg", "ɾ����λ�ɹ�");
		} else {
			resultMap.put("flag", false);
			resultMap.put("msg", "ɾ����λʧ��");
		}
		return resultMap;
	}

	@Override
	public CarVO getCarById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		CarVO carVO = (CarVO) baseDAO.findOneBy(CarVO.class.getName()+".getCarById", paramMap);
		DictionaryItemVO diVO = iDictionaryService.getContentByCodeAndId(carVO.getState(),DictionaryIdContant.CAR_LOCATION_STATE);
		carVO.setState_name(diVO.getContent());
		return carVO;
	}

	@Override
	public List<CarVO> getAllCarLocationListsByState(int state) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("state", state);
		List<CarVO> resultList= (List<CarVO>) baseDAO.findListBy(CarVO.class.getName()+".getCarLocationLists", paramMap);
		for(CarVO carVO : resultList){
			DictionaryItemVO diVO = iDictionaryService.getContentByCodeAndId(carVO.getState(),DictionaryIdContant.CAR_LOCATION_STATE);
			carVO.setState_name(diVO.getContent());
		
		}
		return resultList;
	}
	
	
	
	
}

package com.cm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.vo.CarVO;
import com.cm.vo.FamilyVO;

@Service
public interface ICarLocationService {
	
	/**
	 * 查看小区未购买车位信息列表
	 * @return
	 */
	List<CarVO> getCarLocationLists(); 
	
	/**
	 * 购买车位
	 * @param jfCode
	 * @param carId
	 * @return
	 */
	Map<String,Object> buy(int jfCode,int carId,int userId,int licenseId,int num,double allPrice);
	
	List<CarVO> getYJFInfo(int userId);
	
	Map<String,Object> xf(int car_location_id,int user_id,Date userable_time,int jfCode,int num,double allPrice);
	
	/**
	 * 获取所有车位信息
	 * @return
	 */
	List<CarVO> getAllCarLocationLists();
	
	/**
	 * 
	 * @param carVO
	 * @return
	 */
	Map<String,Object> addCar(CarVO carVO);
	/**
	 * 
	 * @param carVO
	 * @return
	 */
	Map<String,Object> deleteCar(int id);
	
	CarVO getCarById(int id);
	/**
	 * 根据状态筛选
	 * @param state
	 * @return
	 */
	List<CarVO> getAllCarLocationListsByState(int state);
}

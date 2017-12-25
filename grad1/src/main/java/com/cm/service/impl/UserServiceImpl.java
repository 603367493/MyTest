package com.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.common.BaseDAO;
import com.cm.common.DoubleUtils;
import com.cm.service.IUserService;
import com.cm.vo.LicenseVO;
import com.cm.vo.UserVO;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	public BaseDAO baseDAO;

	@Override
	public Map<String, Object> login(String acc, String pwd) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		paramMap.put("acc", acc);
		paramMap.put("pwd", pwd);
		UserVO user =  (UserVO) baseDAO.findOneBy(UserVO.class.getName()+".login", paramMap);//通过
		//查看是否为空
		if(user == null){
			resultMap.put("flag", "false");
			resultMap.put("msg", "账号或者密码错误");
		}else{
			resultMap.put("flag", "true");
			resultMap.put("msg", "登录成功");
			resultMap.put("user", user);
		}
		return resultMap;
	}

	@Override
	public int updateLoginTime(Map<String,Object> paramMap) {

		return baseDAO.update(UserVO.class.getName()+".updateLastTime", paramMap);
	}
//修改密码
	@Override
	public Map<String, Object> updatePwd(String pwd, int user_id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("id", user_id);
			paramMap.put("pwd", pwd);
			//修改密码
			int i  = baseDAO.update(UserVO.class.getName()+".updatePwd", paramMap);
			//查看修改结果
			if(i > 0){
				resultMap.put("flag", true);
				resultMap.put("msg", "修改密码成功");
			}else{
				resultMap.put("flag", false);
				resultMap.put("msg", "修改密码失败");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "修改密码失败");
		}
		
		
		return resultMap;
	}

	@Override
	public List<UserVO> getUserList() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<UserVO> resultList = (List<UserVO>) baseDAO.findListBy(UserVO.class.getName()+".getUserList", paramMap);
		
		for(UserVO userVo:resultList){
			Map<String,Object> licenseParamMap = new HashMap<String,Object>();
			licenseParamMap.put("user_id", userVo.getId());
			List<LicenseVO> licenseList = (List<LicenseVO>) baseDAO.findListBy(LicenseVO.class.getName()+".getLicenseById", licenseParamMap);
			String license_name = "";
			
			for(LicenseVO licenseVo:licenseList){
				license_name+=licenseVo.getLicense()+",";
			}
			license_name = license_name.substring(0,license_name.length()-1);
			userVo.setLicense_name(license_name);
		}
		
		
		return resultList;
	}

	@Override
	public UserVO getUserById(int user_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_id", user_id);
		UserVO userVO = (UserVO) baseDAO.findOneBy(UserVO.class.getName()+".getUserByIdOrAcc", paramMap);
		return userVO;
	}

	@Override
	public Map<String, Object> add(UserVO userVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("acc", userVO.getAcc());
			
			UserVO uVO = (UserVO) baseDAO.findOneBy(UserVO.class.getName()+".getUserByIdOrAcc", paramMap);//查找位置
			//UserVO uVO = (UserVO) baseDAO.findOneBy("林智烽lin", paramMap);//查找位置

			int i = -1;
			if(userVO.getId() == -1){
				if(uVO !=null){
					resultMap.put("flag", false);
					resultMap.put("msg", "账号已存在，无法新增");
					return resultMap;
				}else{
					i = baseDAO.insert(UserVO.class.getName()+".addUser", userVO);
					if(i > 0){
						resultMap.put("flag", true);
						resultMap.put("msg", "添加用户成功");
					}else{
						resultMap.put("flag", false);
						resultMap.put("msg", "添加用户失败");
					}
				}
			}else{
				i = baseDAO.update(UserVO.class.getName()+".updateUser", userVO);
				if(i > 0){
					resultMap.put("flag", true);
					resultMap.put("msg", "修改用户成功");
				}else{
					resultMap.put("flag", false);
					resultMap.put("msg", "修改用户失败");
				}
			}

				
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			resultMap.put("flag", false);
			resultMap.put("msg", "添加用户失败，请联系管理员");
		}
		
		
		
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteUser(UserVO userVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int i = baseDAO.update(UserVO.class.getName()+".updateUser", userVO);
		if(i > 0){
			resultMap.put("flag", true);
			resultMap.put("msg", "删除用户成功");
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "删除用户失败");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updateUser(UserVO userVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int i = baseDAO.update(UserVO.class.getName()+".updateUser", userVO);
		if(i > 0){
			resultMap.put("flag", true);
			resultMap.put("msg", "更新用户成功");
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "更新用户失败");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updatePrice(UserVO userVO) {
		Map<String,Object> resultMap = new HashMap<String,Object>();

		UserVO oldUser = getUserById(userVO.getId());
		if(oldUser == null){
			resultMap.put("flag", false);
			resultMap.put("msg", "系统出错，联系管理员");
			return resultMap;
		}
		double finallyPrice = DoubleUtils.add(userVO.getPrice(), oldUser.getPrice()).doubleValue();
		
		userVO.setPrice(finallyPrice);
		int i = baseDAO.update(UserVO.class.getName()+".updateUser", userVO);
		if(i > 0){
			resultMap.put("flag", true);
			resultMap.put("msg", "充值成功");
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "充值失败");
		}
		return resultMap;
	}

}

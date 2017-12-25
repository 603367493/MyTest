package com.cm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.service.ICarLocationService;
import com.cm.vo.CarVO;
import com.cm.vo.UserVO;

@Controller
@RequestMapping("/carLocation")
public class CarLocationController {

	@Autowired
	public ICarLocationService iCarLocationService;
	
	@RequestMapping("/toCarLocationUI.do")//返回车位界面
	public String toCarLocationUI(HttpServletRequest request,HttpServletResponse response){
		return "carLocation";
	}
	
	@RequestMapping("/getCarLocationInfo.do")
	@ResponseBody
	public List<CarVO> getCarLocationInfo(){
		return iCarLocationService.getCarLocationLists();///返回数值
	}
	
	@RequestMapping("/toBuyCarLocationUI.do")
	public String toBuyCarLocationUI(HttpServletRequest request,int id){
		request.setAttribute("carId", id);
		return "buyCarLocation";//返回购买车位页面
	}
	
	@RequestMapping("/toXFUI.do")//返回页面
	public String toXFUI(HttpServletRequest request,int id,String userable_time){
		request.setAttribute("userable_time", userable_time);
		request.setAttribute("id", id);
		return "xfCarLocation";
	}
	/**
	 * 购买车位
	 * @param request
	 * @param jfCode
	 * @param carId
	 * @param userId
	 * @param licenseId
	 * @return
	 */
	@RequestMapping("/buy.do")
	@ResponseBody//购买车位数据
	public Map<String,Object> buy(HttpServletRequest request,int jfCode,int carId,int userId,int licenseId,int num,Double allPrice){
		return iCarLocationService.buy(jfCode, carId, userId,licenseId,num,allPrice);
	}
	
	@RequestMapping("/toCheckJFUI.do")//返回查询页面
	public String toCheckJF(HttpServletRequest request){
		return "checkJF";
	}
	
	@RequestMapping("/getYJFInfo.do")
	@ResponseBody//返回购买的id
	public List<CarVO> getYJFInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iCarLocationService.getYJFInfo(user.getId());
	}
	
	/**
	 * 续费
	 * @throws ParseException 
	 */
	@RequestMapping("/xf.do")
	@ResponseBody
	public Map<String,Object> xf(HttpServletRequest request,int car_location_id,long userable_time,int jfCode,int num,Double allPrice) throws ParseException{
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		Date date = new Date(userable_time);
		return iCarLocationService.xf(car_location_id, user.getId(), date, jfCode,num,allPrice);
	}
	
	@RequestMapping("/toCarManager.do")
	public String toCarManager(HttpServletRequest request){
		return "carManager";
	}
	
	@RequestMapping("/getCarListByState.do")
	@ResponseBody
	public List<CarVO> getAllCarList(HttpServletRequest request,Integer state){
		return iCarLocationService.getAllCarLocationListsByState(state);
	}
   /**
    * 添加车位
    * @param request
    * @param flag
    * @param id
    * @return
    */
	@RequestMapping("/toAddCarLocationUI.do")
	public String toAddCarLocationUI(HttpServletRequest request,Integer flag,@RequestParam(required=false)Integer id){
		if(flag != 1){
			CarVO carVO = iCarLocationService.getCarById(id);//获得id
			request.setAttribute("carVO", carVO);//设置属性
		}
		request.setAttribute("flag", flag);
		return "addCarLocation";//返回页面
	}
	//添加车位
	@RequestMapping(value="addCar",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addCar(HttpServletRequest request,@RequestBody CarVO carVO){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		//设置新增的车属性
		carVO.setPublish_name(user.getName());//名字
		carVO.setPublish_time(new Date(System.currentTimeMillis()));//时间
		carVO.setPublish_id(user.getId());//获得id
		return iCarLocationService.addCar(carVO);//返回
	}
	
	@RequestMapping(value="deleteCar")
	@ResponseBody//删除车位
	public Map<String,Object> deleteCar(HttpServletRequest request,Integer id){
		return iCarLocationService.deleteCar(id);//返回要删除的id
	}
}

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
	
	@RequestMapping("/toCarLocationUI.do")//���س�λ����
	public String toCarLocationUI(HttpServletRequest request,HttpServletResponse response){
		return "carLocation";
	}
	
	@RequestMapping("/getCarLocationInfo.do")
	@ResponseBody
	public List<CarVO> getCarLocationInfo(){
		return iCarLocationService.getCarLocationLists();///������ֵ
	}
	
	@RequestMapping("/toBuyCarLocationUI.do")
	public String toBuyCarLocationUI(HttpServletRequest request,int id){
		request.setAttribute("carId", id);
		return "buyCarLocation";//���ع���λҳ��
	}
	
	@RequestMapping("/toXFUI.do")//����ҳ��
	public String toXFUI(HttpServletRequest request,int id,String userable_time){
		request.setAttribute("userable_time", userable_time);
		request.setAttribute("id", id);
		return "xfCarLocation";
	}
	/**
	 * ����λ
	 * @param request
	 * @param jfCode
	 * @param carId
	 * @param userId
	 * @param licenseId
	 * @return
	 */
	@RequestMapping("/buy.do")
	@ResponseBody//����λ����
	public Map<String,Object> buy(HttpServletRequest request,int jfCode,int carId,int userId,int licenseId,int num,Double allPrice){
		return iCarLocationService.buy(jfCode, carId, userId,licenseId,num,allPrice);
	}
	
	@RequestMapping("/toCheckJFUI.do")//���ز�ѯҳ��
	public String toCheckJF(HttpServletRequest request){
		return "checkJF";
	}
	
	@RequestMapping("/getYJFInfo.do")
	@ResponseBody//���ع����id
	public List<CarVO> getYJFInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		return iCarLocationService.getYJFInfo(user.getId());
	}
	
	/**
	 * ����
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
    * ��ӳ�λ
    * @param request
    * @param flag
    * @param id
    * @return
    */
	@RequestMapping("/toAddCarLocationUI.do")
	public String toAddCarLocationUI(HttpServletRequest request,Integer flag,@RequestParam(required=false)Integer id){
		if(flag != 1){
			CarVO carVO = iCarLocationService.getCarById(id);//���id
			request.setAttribute("carVO", carVO);//��������
		}
		request.setAttribute("flag", flag);
		return "addCarLocation";//����ҳ��
	}
	//��ӳ�λ
	@RequestMapping(value="addCar",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addCar(HttpServletRequest request,@RequestBody CarVO carVO){
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		//���������ĳ�����
		carVO.setPublish_name(user.getName());//����
		carVO.setPublish_time(new Date(System.currentTimeMillis()));//ʱ��
		carVO.setPublish_id(user.getId());//���id
		return iCarLocationService.addCar(carVO);//����
	}
	
	@RequestMapping(value="deleteCar")
	@ResponseBody//ɾ����λ
	public Map<String,Object> deleteCar(HttpServletRequest request,Integer id){
		return iCarLocationService.deleteCar(id);//����Ҫɾ����id
	}
}

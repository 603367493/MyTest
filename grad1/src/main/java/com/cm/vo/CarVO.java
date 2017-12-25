package com.cm.vo;

import java.util.Date;

public class CarVO {

	public int id;
	
	public String no;
	
	public int state;
	
	public String state_name;
	
	
	public int user_id;
	
	public String user_name;
	
	
	//³µÅÆid
	public String license_id;
	//³µÅÆºÅ
	public String license_name;
	
	public Date userable_time;
	
	public String pay_flag;
	
	public int pay_id;
	
	public int pay_name;
	
	public int delete_flag;
	
	private Date publish_time;
	
	private int publish_id;
	
	private String publish_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getUserable_time() {
		return userable_time;
	}

	public String getLicense_id() {
		return license_id;
	}

	public void setLicense_id(String license_id) {
		this.license_id = license_id;
	}

	public String getLicense_name() {
		return license_name;
	}

	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}

	public void setUserable_time(Date userable_time) {
		this.userable_time = userable_time;
	}

	public String getPay_flag() {
		return pay_flag;
	}

	public void setPay_flag(String pay_flag) {
		this.pay_flag = pay_flag;
	}

	public int getPay_id() {
		return pay_id;
	}

	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public int getPay_name() {
		return pay_name;
	}

	public void setPay_name(int pay_name) {
		this.pay_name = pay_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public int getPublish_id() {
		return publish_id;
	}

	public void setPublish_id(int publish_id) {
		this.publish_id = publish_id;
	}

	public String getPublish_name() {
		return publish_name;
	}

	public void setPublish_name(String publish_name) {
		this.publish_name = publish_name;
	}
	
}

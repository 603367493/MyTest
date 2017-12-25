package com.cm.vo;

import java.util.Date;

/**
 * Ë®µç½É·ÑVO
 * @author 
 *
 */
public class SdjfVO {
	private int id;
	
	private int user_id;
	
	private String user_name;
	
	private double  water_price;
	
	private double electricity;
	
	private int state;
	
	private String state_name;
	
	private Date period;
	
	private Date jf_time;
	
	private int delete_flag;
	
	private Date publish_time;
	
	private int publish_id;
	
	private String publish_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getWater_price() {
		return water_price;
	}

	public void setWater_price(double water_price) {
		this.water_price = water_price;
	}

	public double getElectricity() {
		return electricity;
	}

	public void setElectricity(double electricity) {
		this.electricity = electricity;
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

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

	public Date getJf_time() {
		return jf_time;
	}

	public void setJf_time(Date jf_time) {
		this.jf_time = jf_time;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
	
}

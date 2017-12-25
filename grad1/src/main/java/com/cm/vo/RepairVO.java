package com.cm.vo;

import java.util.Date;

public class RepairVO {

	private int id;
	
	private int user_id;
	
	private String user_name;
	
	private int type_id;
	
	private String type_name;
	
	private String content;
	
	public Date publish_time;
	
	private String state;
	
	private String state_name;
	
	private String feedback;
	
	private int feedback_user_id;
	
	private Date feedback_time;
	
	public int delete_flag;

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

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getFeedback_user_id() {
		return feedback_user_id;
	}

	public void setFeedback_user_id(int feedback_user_id) {
		this.feedback_user_id = feedback_user_id;
	}

	public Date getFeedback_time() {
		return feedback_time;
	}

	public void setFeedback_time(Date feedback_time) {
		this.feedback_time = feedback_time;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}

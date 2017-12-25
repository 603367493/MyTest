package com.cm.vo;

import java.util.Date;

public class NewsVO {
	public long id;
	
	public String title;
	
	public String content;
	
	public Date publish_time;
	
	public String publish_name;
	
	public Date modify_time;
	
	public String modify_name;
	
	public int delete_flag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPublish_name() {
		return publish_name;
	}

	public void setPublish_name(String publish_name) {
		this.publish_name = publish_name;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getModify_name() {
		return modify_name;
	}

	public void setModify_name(String modify_name) {
		this.modify_name = modify_name;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


	
	
}

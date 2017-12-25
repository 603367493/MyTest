package com.cm.common;

import java.awt.event.TextEvent;
import java.util.List;
import java.util.Map;

public interface BaseDAO {

	public List<?> findListBy(String statement,Map<String,Object> paramMap);
	
	public  Object findOneBy(String statement,Map<String,Object> paramMap);
	

	public int update(String statement,Map<String,Object> paramMap);
	
	public int update(String statement,Object object);
	
	public int insert(String statement,Object object);
	
	public Object findOneBy(String statement,Object object);
}

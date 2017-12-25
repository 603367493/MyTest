package com.cm.common;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAOImpl extends SqlSessionDaoSupport  implements BaseDAO {
	

	public List<?> findListBy(String statement, Map<String, Object> paramMap) {
		return getSqlSession().selectList(statement, paramMap);
	}

	@Override
	public Object findOneBy(String statement, Map<String, Object> paramMap) {
		return getSqlSession().selectOne(statement,paramMap);
	}

	@Override
	public int update(String statement, Map<String, Object> paramMap) {
		return getSqlSession().update(statement, paramMap);
	}
	@Override
	public int insert(String statement, Object object) {
		return getSqlSession().insert(statement,object);
	}

	@Override
	public int update(String statement, Object object) {
		return getSqlSession().update(statement,object);
	}

	@Override
	public Object findOneBy(String statement, Object object) {
		return getSqlSession().selectOne(statement, object);
	}

}

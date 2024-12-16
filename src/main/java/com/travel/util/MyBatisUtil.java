package com.travel.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;

	@Autowired
	public MyBatisUtil(SqlSessionFactory sqlSessionFactory) {
		MyBatisUtil.sqlSessionFactory = sqlSessionFactory;
	}

	//添加一个方法用于获取SqlSession对象，从而和数据库进行交互
	public static SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

	//添加一个方法用于获取SqlSessionFactory对象
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
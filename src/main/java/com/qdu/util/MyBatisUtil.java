package com.qdu.util;

import java.io.IOException;
import java.io.InputStream;

import config.MyBatisConfig;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		InputStream inputStream=null;
		try {
			//Resources累的getResourceAsStream()方法用于加载一个资源，并返回一个InputStream对象，用于操作该资源。这里是加载mybatis配置文件
			inputStream = Resources.getResourceAsStream("com/qdu/config/mybatis-config.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(new MyBatisConfig().sqlSessionFactory());

			//SqlSessionFactoryBuilder类用于创建SqlSessionFactory对象
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

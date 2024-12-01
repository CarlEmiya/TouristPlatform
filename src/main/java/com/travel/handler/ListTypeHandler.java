package com.travel.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

//自定义一个类型处理器，来实现java的List<String>类型到数据库的VARCHAR类型的转换
//有多种方式来创建类型处理器，其中一种就是继承一个方便类BaseTypeHandler
//<>种指定要操作的java类型，本程序中是List<String>类型

//使用@MappedJdbcTypes注解指定要操作的数据库类型，可以指定多个，多个以数组形式给出
@MappedJdbcTypes({JdbcType.VARCHAR,JdbcType.CHAR,JdbcType.NVARCHAR,JdbcType.NCHAR})
public class ListTypeHandler extends BaseTypeHandler<List<String>>{

	//setNonNullParameter()方法主要针对插入和更新操作
	//实现从Java的List<String>类型转换为数据库的VARCHAR类型
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<String> hobbies, JdbcType jdbcType)
			throws SQLException {
		//第三个参数就是传入需要转换的值，目前是一个List<String>表示一个爱好列表
		
		//如果需要反复修改一个字符串的值或追加新值构建一个字符串，可以考虑使用StringBuilder
		StringBuilder sb=new StringBuilder();
		
		for(String x:hobbies) {
			sb.append(x).append(";");
		}
		
		//将StringBuilder转换为String类型
		String result=sb.toString();
		//针对插入或更新操作，设置转换后的值为对应位置的参数
		ps.setString(i, result);
	}

	//getNullableResult()方法针对查询操作
	//实现从数据库的VARCHAR类型到Java的List<String>类型的转换
	@Override
	public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		//根据列名获取对应列的数据，这里获取的是数据库的爱好列的值，格式：唱歌;跳舞;画画;看电影;
		String hobbies=rs.getString(columnName);
		String[] array=hobbies.split(";"); //根据;分割爱好
		//Arrays类的asList()方法可以将传入的多个值转换为一个列表
		List<String> list=Arrays.asList(array);
		return list; //返回转换后的结果
	}

	@Override
	public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		//根据列名获取对应列的数据，这里获取的是数据库的爱好列的值，格式：唱歌;跳舞;画画;看电影;
		String hobbies=rs.getString(columnIndex); //有的时候我们会根据列索引获取列的值
		String[] array=hobbies.split(";"); //根据;分割爱好
		//Arrays类的asList()方法可以将传入的多个值转换为一个列表
		List<String> list=Arrays.asList(array);
		return list; //返回转换后的结果
	}
	//有的时候，可能是调用一个存储过程来查询数据库中的数据，调用存储过程需要使用CallableStatement来调用
	@Override
	public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		//根据列名获取对应列的数据，这里获取的是数据库的爱好列的值，格式：唱歌;跳舞;画画;看电影;
		String hobbies=cs.getString(columnIndex); //有的时候我们会根据列索引获取列的值
		String[] array=hobbies.split(";"); //根据;分割爱好
		//Arrays类的asList()方法可以将传入的多个值转换为一个列表
		List<String> list=Arrays.asList(array);
		return list; //返回转换后的结果
	}

}

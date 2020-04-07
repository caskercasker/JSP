package com.sist.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class CreateSqlSessionFactory {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf= new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public static SqlSessionFactory getSsf() {
		return ssf;
	}

	public static void setSsf(SqlSessionFactory ssf) {
		CreateSqlSessionFactory.ssf = ssf;
	}
		
}

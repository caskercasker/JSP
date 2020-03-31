package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class FoodDAO {
	public static SqlSessionFactory ssf;
	
	static {
		try {
			//xml읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//xml파싱
			ssf=new SqlSessionFactoryBuilder().build(reader);
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static List<CategoryVO> categoryListData() {
		SqlSession session = ssf.openSession();
		//connection
		List<CategoryVO> list = session.selectList("categoryListData");
		
		session.close(); //connection 반환
		return list;
	}
	
	public static List<FoodVO> middleListData(int cno) {
		SqlSession session = ssf.openSession();
		//connection
		List<FoodVO> list = session.selectList("middleListData",cno);
		
		session.close(); //connection 반환
		return list;
	}
	
	public static FoodVO detailData(int no) {
		SqlSession session = ssf.openSession();
		//connection
		FoodVO list = session.selectOne("detailData",no);
		
		session.close(); //connection 반환
		return list;
	}
	
}

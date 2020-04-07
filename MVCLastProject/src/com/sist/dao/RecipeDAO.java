package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.ChefVO;
import com.sist.vo.RecipeVO;

import java.util.*;
import java.io.*;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	public static List<RecipeVO> recipeListData(Map map){
		SqlSession session = null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		try {
			session = ssf.openSession();
			list = session.selectList("recipeListData", map);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	public static int recipeTotalPage(){
		SqlSession session = null;
		int total = 0;
		try {
			session = ssf.openSession();
			total = session.selectOne("recipeTotalPage");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<ChefVO> chefListData(Map map){
		SqlSession session = null;
		List<ChefVO> list = new ArrayList<ChefVO>();
		try {
			session = ssf.openSession();
			list = session.selectList("chefListData", map);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	public static int chefTotalPage(){
		SqlSession session = null;
		int total = 0;
		try {
			session = ssf.openSession();
			total = session.selectOne("chefTotalPage");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return total;
	}

}

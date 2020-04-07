package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class MainDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	public static List<CategoryVO> mainImageData() {
		SqlSession session = null;
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			session=ssf.openSession();
			list=session.selectList("mainImageData");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("mainImageData():"+e.getMessage());
		}finally{
			if(session!=null)
			session.close();
		}
		return list;
	}
	
	public static int recipeCount() {
		int count=0;
		SqlSession session=null;
		
		try {
			session = ssf.openSession(); //get Connection
			count=session.selectOne("recipeCount");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return count;		
	}
	
	public static List<RecipeVO> mainRecipeData(){
		
		SqlSession session=null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		try {
			session=ssf.openSession();
			list=session.selectList("mainRecipeData");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static FoodVO mainFoodDetailData(int no) {
		SqlSession session=null;
		FoodVO vo = new FoodVO();
		try {
			session=ssf.openSession();
			vo=session.selectOne("mainFoodDetailData",no);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
}

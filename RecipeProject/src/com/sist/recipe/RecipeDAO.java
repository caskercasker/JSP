package com.sist.recipe;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class RecipeDAO {
    private static SqlSessionFactory ssf;
    static {
    	try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    }
    public static void chefInsert(ChefVO vo){
    	SqlSession session = ssf.openSession(true);
    	//auto commit()
    	session.insert("chefInsert",vo);
    	session.close();
    }
    
    public static void recipeInsert(RecipeVO vo){
    	SqlSession session = ssf.openSession(true);
    	session.insert("recipeInsert",vo);
    	session.close();
    }
    
    public static void recipeDetailInsert(RecipeDetailVO vo){
    	SqlSession session = ssf.openSession(true);
    	session.insert("recipeDetailInsert",vo);
    	session.close();
    }
    public static List<RecipeVO> recipeData(){
    	SqlSession session = ssf.openSession();
    	List<RecipeVO> list = session.selectList("recipeData");
    	session.close();
    	
    	return list;
    }
}














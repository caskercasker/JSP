package com.sist.dao;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml"); //xml 가져오기 
			ssf = new SqlSessionFactoryBuilder().build(reader); //xml 파싱
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static int movieTotalPage(){
		int total=0;
		SqlSession session = ssf.openSession();
		total = session.selectOne("movieTotalPage");

		session.close();
		return total;
	}
	public static List<MovieVO> movieAllData(){
		//파싱된 ssf 에서 id를 읽는다. 
		List<MovieVO> list = new ArrayList<MovieVO>();
		SqlSession session = ssf.openSession();	//commit 이 자동으로 작동하지 않음.
		//session.commit();
		// <insert> ==> insert(), <delete> => delete()
		list = session.selectList("movieAllData");
				
		session.close();
		return list;
	}
}
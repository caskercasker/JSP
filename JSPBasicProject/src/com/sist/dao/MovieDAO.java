package com.sist.dao;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader=Resources.getResourceAsReader("config.xml"); //xml 가져오기 
			ssf = new SqlSessionFactoryBuilder().build(reader); //xml 파싱
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static List<MovieVo> movieAllData(){
		//파싱된 ssf 에서 id를 읽는다. 
		return ssf.openSession().selectList("movieAllData");
	}
	
	public static List<MusicVO> musicAllData(){
		//파싱된 ssf 에서 id를 읽는다.  
		return ssf.openSession().selectList("musicAllData");
	}

}
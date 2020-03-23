package com.sist.dao;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

public class BoardDAO {
private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml"); //xml 가져오기 
			ssf = new SqlSessionFactoryBuilder().build(reader); //xml 파싱
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//세션 활용 
	
	
	//기능을 전체에서 연결 해야 되기 때문에 public static 
	
	public static List<BoardVO> boardListData(){
		return ssf.openSession().selectList("boardListData");
	}
	
	public static BoardVO boardDetailData(int a){
		return ssf.openSession().selectOne("boardDetailData");
	}
}

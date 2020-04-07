package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	public static List<ZipcodeVO> postfindData(String dong)	{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		SqlSession session=null;
		try {
			session = ssf.openSession();
			list = session.selectList("postfindData",dong);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
}

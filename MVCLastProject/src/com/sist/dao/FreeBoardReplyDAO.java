package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardReplyVO;
	
public class FreeBoardReplyDAO {
	private static SqlSessionFactory ssf;
	static 
	{
		ssf=CreateSqlSessionFactory.getSsf();
		
	}
	public static List<BoardReplyVO> replyBoardListData(Map map){

		List<BoardReplyVO> list = new ArrayList<BoardReplyVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyBoardListData",map);
			
		// 3개의 IN 을 통해 OUT 형태를 가진 result 를 가짐
		// 반환형태는 CURSOR 이기에 RS 를 통해서 VO에 값을 집어 넣을수 있음. 	
			list =(ArrayList<BoardReplyVO>)map.get("pResult");
			
//			while(rs.next()){
//				BoardReplyVO vo = new BoardReplyVO();
//				vo.setNo(rs.getInt(1));
//				vo.setBno(rs.getInt(2));
//				vo.setId(rs.getString(3));
//				vo.setName(rs.getString(4));
//				vo.setMsg(rs.getString(5));
//				vo.setRegdate(rs.getDate(6));
//				vo.setDbday(rs.getString(7));
//				vo.setGroup_tab(rs.getInt(8));
//				
//				list.add(vo);
//			}
//			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void replyInsert(Map map){
		
		SqlSession session=null;
		try {
			session = ssf.openSession();
			session.update("replyInsert",map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static int replyBoardTotalPage(Map map){
		int total =0;
		SqlSession session=null;
		try {
			session = ssf.openSession();
			session.update("replyBoardTotalPage",map);  //프로시저 호출
			total = (int)map.get("pTotal");
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return total;
	}
}

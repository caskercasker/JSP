package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;


public class MovieDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	//기능 처리
	public static List<MovieVO> movieListData(){
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		SqlSession session=null;
		try {
			session = ssf.openSession();
			list = session.selectList("movieListData");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close(); //반환 => DBCP 
		}
		
		return list;
	}
	
	public static void movieTheaterUpdate(MovieVO vo){
		
		SqlSession session=null;
		try {
			session= ssf.openSession(true);
			session.update("movieTheaterUpdate",vo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static ReserveTheaterVO movieTheaterData(int tno){
		
		ReserveTheaterVO vo = new ReserveTheaterVO();
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			vo=session.selectOne("movieTheaterData",tno);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static void movieDateUpdate(ReserveTheaterVO vo){
		
		SqlSession session=null;
		try {
			session= ssf.openSession(true);
			session.update("movieDateUpdate",vo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static void movieTimeUpdate(ReserveDateVO vo){
		
		SqlSession session=null;
		try {
			session= ssf.openSession(true);
			session.update("movieTimeUpdate",vo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static String movieTimeData(int tno){
		
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			result = session.selectOne("movieTimeData",tno);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		
		return result;
	}
	
	//List => forEach in()
	public static String movieTimeData2(int tno){
		
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			result = session.selectOne("movieTimeData2",tno);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		
		return result;
	}
	public static MemberVO movieLogin(String id, String pwd){
		MemberVO vo = new MemberVO();
		
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count = session.selectOne("movieIdCount",id);
			
			if(count==0){
				vo.setMsg("NOID");
			}
			else{
				//id가 존재
				MemberVO mvo = session.selectOne("movieGetPwd",id);
				
				if(pwd.equals(mvo.getPwd())){
					vo.setMsg("OK");
					vo.setId(mvo.getId());
					vo.setAdmin(mvo.getAdmin());
					vo.setName(mvo.getName());
				}
				else{
					vo.setMsg("NOPWD");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static void movieReserveOk(ReserveVO vo){
		
		SqlSession session=null;
		
		try {
			session = ssf.openSession(true);
			session.insert("movieReserveOk",vo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static List<ReserveVO> movieMyPage(String id){
		
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("movieMyPage",id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static List<ReserveVO> movieAdmin(){
		
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("movieAdmin");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static void adminUPdate(int rno){
		SqlSession session=null;
		
		try {
			session = ssf.openSession(true);
			session.update("adminUpate",rno);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static MovieVO reserveResultData(int mno){
		MovieVO vo = new MovieVO();
		SqlSession session=null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("reserveResultData",mno);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return vo;
	}
}

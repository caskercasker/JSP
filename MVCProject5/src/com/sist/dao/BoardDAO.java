package com.sist.dao;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
/*
 * 자바 클래스 구성요소
	===========
	1) 멤버변수
		= 인스턴스 : new를 이용해서 메모리 할당 => 생성
		= 정적변수(static) : JVM이 클래스를 읽기 시작 => 생성
	2) 메소드
	3) 생성자 

	=====================
	필요시에 멤버 변수에 대한 초기화
	= 명시적 방법 
		public class A
		{
			int a = 10;
			static int b = 20; ==> 선언만 가능( 메소드 호출, 제어문, 연산자 사용이 불가능)
			=============> 외부나 메소드를 이용해서 초기화를 할 수 없다. 
			public int getNumber(){
			}
	= 생성자를 이용하는 방식 : 외부에 파일 읽기, 네트워크 연결, DB 연결 XML을 파싱 
	= 초기화 블록 이용

	순서 
	===
		=> 명시적 방법 => 초기화 블록 => 생성자 
						========
						인스턴스 블록 => 변수가 인스턴스일 경우 
						public class A{
							int a;
							{a = getNumber()
							}
							=====> 인스턴스 ==> 생성
						static 블록 => static 일 경우에 사용 
							public class B {
							int a;
							static {
							a = getNumber()	
							}
	======================= 자동 호출 
 * 
 * 
 */
public class BoardDAO {
private static SqlSessionFactory ssf;
	// 파싱 => getConnection(), disConnection()
	// id,sql문장을 실행
	// 자동으로 시작. 
	
	static { 
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml"); //xml 가져오기  연결...
			// Spring.MyBatix ==> classpath:src (default)
			// 파싱 
			ssf = new SqlSessionFactoryBuilder().build(reader); //xml 파싱
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	//목록 읽기
	public static List<BoardVO> boardListData(Map map){
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session = ssf.openSession();
		list = session.selectList("boardListData",map);
		//범위값에 대한 설정..
		
		//connection 반환
		session.close();
		return list;
	}
	
	public static BoardVO boardDetailData(int no){
		BoardVO vo = new BoardVO();
		SqlSession session = ssf.openSession();	//commit 이 자동으로 작동하지 않음.
		session.update("hitIncrement",no); // <update>
		session.commit();
		// <insert> ==> insert(), <delete> => delete()
		vo = session.selectOne("boardDetailData",no);
				
		session.close();
		return vo;
	}
	//총페이지 구하기
	
	public static int boardTotalPage(){
		int total=0;
		SqlSession session = ssf.openSession();
		total = session.selectOne("boardTotalPage");

		session.close();
		return total;
	}
	//데이터 추가
	public static void boardInsert(BoardVO vo){
		SqlSession session = ssf.openSession(true); //auto commit을 true 	setAutoCommit(true)
		session.insert("boardInsert",vo);
		session.close();
	}
	
	public static BoardVO boardUpdate(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession(true);
		
		vo=session.selectOne("boardUpdate",no);
		
		session.close();
		return vo;
	}
	
	public static void boardUpdateData(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);  //.oepnSession(true): setAutoCommit(true)해준다. 
		session.update("boardUpdateData",vo); 
		session.close();
	}
}

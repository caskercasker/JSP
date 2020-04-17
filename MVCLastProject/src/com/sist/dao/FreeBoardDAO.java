package com.sist.dao;
import java.util.*;

import com.sist.vo.BoardVO;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class FreeBoardDAO {
	
	private Connection conn;
	private CallableStatement cs;												// Procedure 호출시에 전송 객체 
	
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public FreeBoardDAO(){
		try {
																				// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
																				// 연결
	
	// SqlSession session = ssf.openSession();
	public void getConnection(){
		
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
																				//해제 
	//session.close()
	public void disConnection(){
		
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// ########################################################################################
	/*
	 * CREATE OR REPLACE PROCEDURE boardListData(
    	pStart NUMBER,
    	PEnd NUMBER,
    	pResult OUT SYS_REFCURSOR
		)
		
		프리서저명(함수), 매개변수 명 정확하게 
	 * 
	 */
	
	// session.selectList()
	
	public List<BoardVO> freeboardListData(int page) {
	
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			int rowSize = 10;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			
			String sql = "{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql);											//프로시저 호출
	
			// ?에값을 채운다. 
			cs.setInt(1,start);
			cs.setInt(2,end);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			//실행
			cs.executeUpdate();
			
			//Object
			//결과값
			ResultSet rs = (ResultSet)cs.getObject(3);			   				//CURSOR => ResultSet
			Object obj = cs.getObject(3);
			
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
				
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		return list;
	}
	
	//총 페이지
	/*
	 *  
	 *  CREATE OR REPLACE PROCEDURE boardTotalPage(
    	pTotal OUT NUMBER
		)
		IS	
		BEGIN
		    SELECT CEIL(COUNT(*)/10.0) INTO pTotal
		    FROM board;
		END;
		/
	 */
	public int freeboardTotalPage (){
		
		int total=0;
		
		try {
			getConnection();
			String sql = "{CALL boardTotalPage(?)}";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.NUMBER);					//정수 저장할 수 있는 공간 (메모리)를 만들어라.
			
			//실행
			cs.executeUpdate();
			
			//메모리에서 결과값을 읽어온다.
			total = cs.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		return total;
	}
/*
 * CREATE OR REPLACE PROCEDURE boardInsert(
    pName board.name%TYPE,
    pSubject board.subject%TYPE,
    pContent board.content%TYPE,
    pPwd board.pwd%TYPE
)
 */
	public void freeboardInsert(BoardVO vo ){
		
		
		try {
			getConnection();
			String sql = "{CALL boardInsert(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
			
			//실행
			cs.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
	}


	//상세보기, 수정하기
	/*
	 * CREATE OR REPLACE PROCEDURE boardInfoData(
	    pNo board.no%TYPE,
	    pType NUMBER,
	    pResult OUT SYS_REFCURSOR
		)
	 * 
	 * 	
	 */
	public BoardVO freeboardInfoData (int no, int type){
		
		BoardVO vo = new BoardVO();
		
		try {
			getConnection();
			String sql="{CALL boardInfoData(?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setInt(2, type);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.executeUpdate();
			
			ResultSet rs = (ResultSet)cs.getObject(3);
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		
		return vo;
	}
	
	//수정하기
	
/*
 * CREATE OR REPLACE PROCEDURE boardUpdate(
    pNo board.no%TYPE,
    pName board.name%TYPE,
    pSubject board.subject%TYPE,
    pContent board.content%Type,
    pPwd board.pwd%TYPE,
    pResult OUT VARCHAR2
)
 * 
 * 
 */
	public boolean freeboardUpdate(BoardVO vo){
		
		boolean bCheck=false;
		
		try {
			getConnection();
			//함수 호출
			String sql="{CALL boardUpdate(?,?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			//
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getName());
			cs.setString(3, vo.getSubject());
			cs.setString(4, vo.getContent());
			cs.setString(5, vo.getPwd());
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			
			//실행
			cs.executeUpdate();
			
			String result = cs.getString(6);
			
			bCheck = Boolean.parseBoolean(result);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		
		return bCheck;
	}
	
	
	//삭제하기
	public boolean freeboardDelete(int no, String pwd){
		
		boolean bCheck=false;
		
		try {
			getConnection();
			//함수 호출
			String sql="{CALL boardDelete(?,?,?)}";
			cs = conn.prepareCall(sql);
			//
			cs.setInt(1, no);
			cs.setString(2, pwd);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			
			//실행
			cs.executeUpdate();
			String result = cs.getString(3);
			
			bCheck = Boolean.parseBoolean(result);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		
		return bCheck;
	}
	
}

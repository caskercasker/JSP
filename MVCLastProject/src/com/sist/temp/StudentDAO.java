package com.sist.temp;
import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;
public class StudentDAO {
	private Connection conn;
	private CallableStatement cs; // Procedure를 사용 할 떄 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public StudentDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex){}
		
	}
	
	public void disConnection(){
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public StudentVO studentInfoData(int no){
		StudentVO vo = new StudentVO();
		
		try {
			getConnection();
			String sql = "{CALL pro_test_select(?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, no);	// 자바에서 첨부하는 변수 
			// String anme => :name 
			
			//데이터를 받은 변수 설정 
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.registerOutParameter(5, OracleTypes.NUMBER);
			
			//실행  => 프로시저는 모든 SQL => executeUpdate();
			cs.executeUpdate();
			
			String name = cs.getString(2);
			int kor = cs.getInt(3);
			int eng = cs.getInt(4);
			int math = cs.getInt(5);
			
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMath(math);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
		return vo;
	}
}

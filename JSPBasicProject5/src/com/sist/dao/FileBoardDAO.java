package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class FileBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public FileBoardDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// 연결
	
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 연결 해제
	public void disConnection(){
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//목록 출력
	public List<FileBoardVO> boardListData(int page){

		List<FileBoardVO> list = new ArrayList<FileBoardVO>();

		try {
			getConnection();
			String sql="SELECT no,subject,content,name,regdate,hit,num "
					+"FROM (SELECT no,subject,content,name,regdate,hit,rownum as num "
					+"FROM (SELECT no,subject,content,name,regdate,hit "
					+"FROM fileBoard ORDER BY no DESC)) "
					+"WHERE num BETWEEN ? AND ?";
			//왜? between 을 sequence를 사용 할 수 없다. 
			
			int rowSize=10;
			int start =(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				FileBoardVO vo = new FileBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				vo.setRownum(rs.getInt(7));

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
	
	//데이터 추가
	public void boardInsert(FileBoardVO vo){
		try {
			getConnection();
			
			String sql="INSERT INTO fileBoard VALUES("
					+"fb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1,vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			
			//실행
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			disConnection();
		}
	}
	public FileBoardVO boardDetailData(int no){
		FileBoardVO vo = new FileBoardVO();

		try{
			getConnection();
			String sql="UPDATE fileBoard SET "
						+"hit=hit+1 "
						+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql="SELECT no,name,subject,content,regdate,hit,filename,filesize "
				+"FROM fileBoard "
				+"WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			
			rs.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			disConnection();
		}
		return vo;
	}
}

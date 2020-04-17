package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class ReplyBoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<BoardVO> replyListData(Map<?, ?> map){
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("replyListData",map);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int replyTotalPage(){
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("replyTotalPage");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return total;
	}

	public static BoardVO replyDetailPage(int no){
		BoardVO vo = new BoardVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.update("replyIncrement",no);
			session.commit();
			vo = session.selectOne("replyDetailPage",no);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static void replyInsert(BoardVO vo){

		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.insert("replyInsert",vo);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	public static void replyUpdate(BoardVO vo){
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.update("replyUpdate",vo);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
			session.close();
		}
	}
	
	
	
	public static BoardVO replyBoardUpdate(int no)
	{
		BoardVO vo = new BoardVO();
		SqlSession session = null;
		try {	
			session = ssf.openSession();
			vo = session.selectOne("replyDetailPage",no);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	// 비밀번호 가져오기
	public static String replyGetPassword(int no){
		String pwd="";
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			pwd = session.selectOne("replyGetPassword",no);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			if(session!=null)
			session.close();
		}
		return pwd;
	}
		
	// 답변형 게시판 댓글 추가 , 4개의 SQL문을 실행하여 결과값을 가져온다.
	public static void replyReplyInsert(int pno, BoardVO vo){
		SqlSession session=null;
		try {
			session=ssf.openSession();
			BoardVO pvo = session.selectOne("replyParentInfoData",pno);
			session.update("replyGroupStepIncrement",pvo);
						
			//replyReplyInsert , 들어온 PVO에서 읽어온 값에서 
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			
			
			session.insert("replyReplyInsert",vo);
			session.update("replyDepthIncrement",pno);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			session.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	// 답글현 게시판 댓글 삭제, 비번 확인 부터 ==> 처리 
	public static boolean replyDelete(int no, String pwd){
		
		boolean bCheck=false;
		SqlSession session = null;
		
		try {
			session=ssf.openSession();
			String db_pwd = session.selectOne("replyGetPassword",no);
			if(db_pwd.equals(pwd)){
				bCheck=true;
				BoardVO vo = session.selectOne("replyDeleteInfoData", no);
				if(vo.getDepth()==0){
					session.selectOne("replyDelete",no);
				}else{
					vo.setSubject("관리자가 삭제한 게시물 입니다");
					vo.setContent("관리자가 삭제한 게시물 입니다 ");
					vo.setNo(no); //해당 게시물 번호 (mybatix 세팅에서 vo를 받게 되어 있다)
					session.selectOne("replySubjectUpdate",vo);
				}
				session.update("replyDepthDecrement",vo.getRoot());
			}else{
				bCheck=false;
			
			}
					
					
					
					
					
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			session.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
		
		return bCheck;
	}
}	


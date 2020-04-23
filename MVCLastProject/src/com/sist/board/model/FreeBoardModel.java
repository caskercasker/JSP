package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardReplyVO;
import com.sist.vo.BoardVO;

/*							Model => DAO,Service,VO
 * 	사용자 요청 (page) ======>  Model(DAO)  ========================> JSP 출력
 * 							======
 * 							@RequestMapping(사용자가 요청한 URI 주소)
 */

@Controller
public class FreeBoardModel {
	
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response){

		//page
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		
		FreeBoardDAO dao = new FreeBoardDAO();
		List<BoardVO> list = dao.freeboardListData(curpage);
		int totalpage = dao.freeboardTotalPage();
		
		request.setAttribute("list", list);		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response){
		
		//메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//							============= ~VO(구조체(여러개의 변수를 묶어준다) ==> 클래스)
		
		// 사용자가 보낸 데이터를 받는다. (post 방식으로 인코딩 되에 넘어 왔기 때문에  재 인코딩)
		try {
			request.setCharacterEncoding("UTF-8");  // 디코딩 (송신:인코딩, 수신: 디코딩)
			//ASC (1byte) -> Unicode(2byte)
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO로 전송 => DAO에서 오라클로 보내준다.
		FreeBoardDAO dao = new FreeBoardDAO();
		//insert 요청
		dao.freeboardInsert(vo);
				
		return "redirect:../freeboard/list.do";
	}

	
	//################################################################################################ 댓글 프로시저  실행 (추가)
	//상세보기
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response){
		
		String no = request.getParameter("no");
		//* 추가
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end = curpage*rowSize;
		
		Map map=new HashMap<>();
		map.put("pStart", start);
		map.put("pEnd",end);
		map.put("pBno", Integer.parseInt(no));
		List<BoardReplyVO> list = FreeBoardReplyDAO.replyBoardListData(map);
		
		map = new HashMap<>();
		map.put("pBno", Integer.parseInt(no));
		int totalpage = FreeBoardReplyDAO.replyBoardTotalPage(map);
		request.setAttribute("list",list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		//추가 end
		FreeBoardDAO dao = new FreeBoardDAO();
		BoardVO vo = dao.freeboardInfoData(Integer.parseInt(no), 1);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		//no를 주고 vo를 받는다.
		FreeBoardDAO dao = new FreeBoardDAO();
		//vo를 받아온다
		BoardVO vo = dao.freeboardInfoData(Integer.parseInt(no), 2);
		//jsp로 보내준다. 
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response){
		
		//메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//							============= ~VO(구조체(여러개의 변수를 묶어준다) ==> 클래스)
		
		// 사용자가 보낸 데이터를 받는다. (post 방식으로 인코딩 되에 넘어 왔기 때문에  재 인코딩)
		try {
			request.setCharacterEncoding("UTF-8");  // 디코딩 (송신:인코딩, 수신: 디코딩)
			//ASC (1byte) -> Unicode(2byte)
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		// DAO로 전송 => DAO에서 오라클로 보내준다.
		FreeBoardDAO dao = new FreeBoardDAO();
		//Update 요청
		boolean bCheck = dao.freeboardUpdate(vo);
		
		request.setAttribute("bCheck", bCheck);		
		request.setAttribute("no", no);
		return "../freeboard/update_ok.jsp";
	}
	/*
	 * 	사용자
	 * 	=> delte.do?no=10 		===> Model				======> 요청한 jsp로 값을 전송 (화면에 출력)
	 * 								======
	 * 								사용자가 보내준 값을 받는다. 
	*/
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request,HttpServletResponse response){
		String no = request.getParameter("no");
		
		request.setAttribute("no", no);
		request.setAttribute("main_jsp", "../freeboard/delete.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		//DAO 연결
		FreeBoardDAO dao = new FreeBoardDAO();
		boolean bCheck = dao.freeboardDelete(Integer.parseInt(no), pwd);
		
		request.setAttribute("bCheck", bCheck);
		return "../freeboard/delete_ok.jsp";
	}
}

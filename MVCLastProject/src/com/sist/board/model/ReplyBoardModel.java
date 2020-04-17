package com.sist.board.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

@Controller
public class ReplyBoardModel {
	@RequestMapping("reply/list.do")
	public String reply_list(HttpServletRequest request, HttpServletResponse response){
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize = 15;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end",end);
		
		// => DAO 처리
		List<BoardVO> list = ReplyBoardDAO.replyListData(map);
		int totalpage= ReplyBoardDAO.replyTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		/*
		 * 	Class Model {
		 *  	HttpServletRequest request;
		 *      public Model(HttpServletRequest request){
		 *      	this.request.request;
		 *      }
		 *      public void addAttribute(String key,Object obj){
		 *      	request.setAttribute(key,obj);	
		 *      }
		 *      
		 *      Model model = new Model(request);
		 *      model.addAttribute("list",list)
		 *      
		 * 	}
		 * 
		 */
		request.setAttribute("main_jsp", "../reply/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/detail.do")
	public String replay_detail(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		BoardVO vo = ReplyBoardDAO.replyDetailPage(Integer.parseInt(no));

		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("main_jsp", "../reply/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/insert_ok.do")
	public String reply_inser_ok(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);

		ReplyBoardDAO.replyInsert(vo);
		
		return "redirect:../reply/list.do";
	}
	@RequestMapping("reply/update.do")
	public String reply_modify(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		BoardVO vo= ReplyBoardDAO.replyDetailPage(Integer.parseInt(no));
		
		System.out.println(no);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/update_ok.do")
	public String reply_update_ok(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String no = request.getParameter("no");
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
			
		BoardVO vo= ReplyBoardDAO.replyBoardUpdate(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO.replyUpdate(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/detail.jsp?"+"no="+no);
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/password_check.do")
	public String reply_password_check(HttpServletRequest request,HttpServletResponse response){
		String no =request.getParameter("no");
		String pwd = request.getParameter("pwd");
		System.out.println(no);
		System.out.println(pwd);
		String db_pwd=ReplyBoardDAO.replyGetPassword(Integer.parseInt(no));
		int res=0;
		if(db_pwd.equals(pwd)){
			res=1;
		}else{
			res=0;
		}
		request.setAttribute("result", res);

		return "../reply/password_check.jsp";
	}
	
	@RequestMapping("reply/reply.do")
	public String reply_reply(HttpServletRequest request, HttpServletResponse response){
		String pno = request.getParameter("no");
		request.setAttribute("pno", pno); //답변을 달 상위 글의 번호  (Parent no)
		request.setAttribute("main_jsp", "../reply/reply.jsp");
		return "../main/main.jsp";
	}
// 댓글 등록 #######################################################################################	
	@RequestMapping("reply/reply_ok.do")
	public String reply_reply_ok(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String pno=request.getParameter("pno");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//DAO 연결
		//DB 연동 pno를 가져오고 DB에 서 데이터한 처리를 하고 vo에 입력된 데이터를 실어준다. 
		ReplyBoardDAO.replyReplyInsert(Integer.parseInt(pno), vo);
// 		reply_list() => 재호출		
		return "redirect:../reply/list.do"; //메소드를 다시 호출 
	}
	
	
//댓글형 게시판 삭제 ###############################################################################	
	@RequestMapping("reply/delete.do")
	public String reply_delete(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		
		request.setAttribute("no", no);
		request.setAttribute("main_jsp", "../reply/delete.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/delete_ok.do")
	public String reply_delete_ok(HttpServletRequest request, HttpServletResponse response){
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		//DAO 연결
		boolean bCheck = ReplyBoardDAO.replyDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
		
		//id 와 패스워드에 대한 데이터를 보낸다. 
		return "../reply/delete_ok.jsp";
	}
}

package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateOkModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no="";
		try{
			request.setCharacterEncoding("UTF-8");
			no = request.getParameter("no");
			String name=request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");
			
			BoardVO vo = new BoardVO();
			vo.setNo(Integer.parseInt(no));
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardUpdateData(vo);
			System.out.println("보냈다");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "redirect:detail.do?no="+no;
	}

}

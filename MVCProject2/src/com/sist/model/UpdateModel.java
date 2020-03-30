package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	
			String no=request.getParameter("no");
			BoardVO vo=BoardDAO.boardUpdate(Integer.parseInt(no));
			// update.jsp로 전송
			request.setAttribute("vo", vo);

		return "board/update.jsp";
	}

}
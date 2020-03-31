package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class ReserveModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		request.setAttribute("main_jsp", "../movie/reserve.jsp");
		request.setAttribute("msg", "예약화면입니다");
		return "../main/main.jsp";
	}

}

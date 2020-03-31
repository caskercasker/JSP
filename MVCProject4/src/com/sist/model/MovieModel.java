package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class MovieModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("main_jsp", "../movie/movie.jsp");
		request.setAttribute("msg", "영화메인");
		
		return "../main/main.jsp";
	}

}

package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

public class MovieListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String page=request.getParameter("page");
		//유효성 검사
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		List<MovieVO> list = MovieDAO.movieAllData();
		int totalpage=MovieDAO.movieTotalPage();
		
		//활용할 값, 현재페이지, 페이지에 존재하는 데이터 (MovieBean이라는 형태의 리스트)
		//전체페이지 출력을 위한 메소드 
		
		//request로 온 데이터를 받아서 처리하고 화면에 뿌려질 jsp의 request로 보낸다. 
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		//필요한 만큼 key 와 value를 통해서 보낸다. 
/*		
		HttpSession session=request.getSession();
		session.setAttribute("list", list);
		session.setAttribute("curpage", curpage);
		session.setAttribute("totalpage", totalpage);
		*/
		
		
		return "board/movie_list.jsp";
	}

}

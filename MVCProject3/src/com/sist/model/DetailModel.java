package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;



public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		FoodVO vo = FoodDAO.detailData(Integer.parseInt(no));
		
		//detail.jsp로 전송
		request.setAttribute("vo", vo);
		return "food/detail.jsp";
	}

}

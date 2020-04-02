package com.sist.board.model;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.common.model.CommonModel;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

@Controller
public class BoardModel {
	@RequestMapping("site/board/list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response){

		List<EmpVO> list = EmpDAO.empAllData();
		request.setAttribute("list", list);
		request.setAttribute("msg", "자유게시판");
		request.setAttribute("main_jsp", "board/list.jsp");
		CommonData.commonData(request);
		
		return "../main.jsp";
	}
}

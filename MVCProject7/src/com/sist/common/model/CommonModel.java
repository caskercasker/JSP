package com.sist.common.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class CommonModel {
	@RequestMapping("site/main.do")
	public String main_page(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("main_jsp", "home.jsp");
		CommonData.commonData(request);
		return "main.jsp";
	}

}

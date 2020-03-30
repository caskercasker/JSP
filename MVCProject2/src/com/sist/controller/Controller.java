package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
import java.util.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 스프링  <bean id="list" class="com.sist.model.ListModel"/>
	private String[] strCmd={"list","insert","insert_ok","detail","update","update_ok","movie_list"};
	private String[] strCls={
			"com.sist.model.ListModel",
			"com.sist.model.InsertModel",
			"com.sist.model.InsertOkModel",
			"com.sist.model.DetailModel",
			"com.sist.model.UpdateModel",
			"com.sist.model.UpdateOkModel",
			"com.sist.model.MovieListModel"
			};
	
		
	private Map clsMap = new HashMap();
	/*
	 * map.put("list",new ListModel())
	 */
	
	/*
	 * 		메모리 할당
	 * 		Controller con  = new Controller();
	 * 		con.init(){
	 * 			초기화 => 생성자
 	 * 		}
	 * 		con.service(){
	 * 		}
	 * 		con.destroy(){
	 * 			System.gc();
	 * 		}
	 */
	
	public void init(ServletConfig config) throws ServletException {
		try {
			for(int i=0;i<strCls.length;i++){
				Class clsName = Class.forName(strCls[i]);
				Object obj = clsName.newInstance(); //메모리 할당 끝 
				System.out.println(obj);
				clsMap.put(strCmd[i], obj);
			}
		} catch (Exception e) {
			
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		//detail.do?no=1		
		// /MVCProject2/*.do
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		Model model = (Model)clsMap.get(cmd);
		
		//System.out.println(model);
		String jsp=model.execute(request);
		
		//System.out.println(jsp);
		if(jsp.startsWith("redirect")){
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
		}else{
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}

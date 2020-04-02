package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> list = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		//path (web.xml)
		String path = config.getInitParameter("contextConfigLocation");
		String defaultPath = config.getInitParameter("defaultPath");
		HandlerMapping hm = new HandlerMapping(path,defaultPath);
		list = hm.getList();
		

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String cmd = request.getRequestURI();  //main.main.do
			cmd=cmd.substring(request.getContextPath().length()+1);
			try {
				/*
				 * com.sist.model.BoardModel...
				 * com.sist.model.HomeModel..
				 */
				for(String cls:list){
					/*
					 * @Controller
					 * class A
					 * class B
					 * @Controller
					 * class C
					 * 
					 * @RequestMapping("main/main.do")
					 * 메소드
					 */
					Class clsName = Class.forName(cls);
					if(clsName.isAnnotationPresent(Controller.class)==false)
						continue; //어노테이션이 없다면 pass
					Object obj = clsName.newInstance();
					
					//메소드를 찾아서 호출
					Method[] methods = clsName.getDeclaredMethods();  //모든 메소드를 가져오라
					
					//어노테이션 처리 
					for(Method m : methods){
						RequestMapping rm = m.getAnnotation(RequestMapping.class);
						if(rm.value().equals(cmd)){
							String jsp=(String)m.invoke(obj, request,response);     //생성된 오브젝트는 request와 response를 매개변수로 가지고 있다. 
							
							//return "redirect:list.do"
							//return "main/list.jsp"
							System.out.println(jsp);
							if(jsp.startsWith("redirect")){
								response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							}else{
								RequestDispatcher rd = request.getRequestDispatcher(jsp);
								rd.forward(request, response);
							}
							return;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	}

}

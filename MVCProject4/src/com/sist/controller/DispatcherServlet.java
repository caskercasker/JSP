package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.sist.model.Model;
import com.sist.temp.Controller;

/* 	어노테이션이 올라갈수 있는 자리 
 * 	@ => TYPE, 클래스 구분 	=======> @Controller, @Repository(DAO), @Componenet, @Service	(메모리 할당 기준)
 * 								// Model	  //DAO				//일반 클래스	 //Manager (ex> 외부 데이터 가져오기 등등)
 * 	public class A 
 * {
 * 	    @ => FIELD (메모리 주소 전송):===>  @AutoWired(자동 메모리 할당) 	
 * 		private B b;
 * 
 * 		public void setB(@ B b)  ==> PARAMETER => @Resource
 * 		{
 * 			this.b = b;
 * 		}
 * 		
 *		@ => CONSTRUCTOR
 * 		public A()	
 * 		{
 * 		
 * 		}
 * 		@ => METHOD
 * 		public void display(){
 * 		}
 * 	}
 * 
 * 	public class A
 * {
 * 		public voi aaa(String a, int b){}
 * 		public void bbb(String a){}
 * 		public ccc(String a, double d){}
 * 	==> a = > aaa, b => bbb, c => ccc 호출
 *  => char input
 *  
 *  
 *  //자동호출이 되지 않기 떄문에 조건문을 주고 불러준다.  자동호출시 매개변수를 어노테이션을 통해서 해결해주어야 한다. 
 *  A aa=new A();
 *  if(input ='a')
 *  	aa.aaa("",10);
 *  else if(input=="b")
 *  	aa.bbb("");
 *  else if(input=="c")
 *  	aa.ccc("",10.5);
 * 
 * }
 */

/*@WebServlet("/DispatcherServlet")*/
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap= new HashMap();
	//웹 컨테이너가 XML을 파싱해서 Map에 해당 주소와 로직을 연결한다.
	
	
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try {
			DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(path));
			
			Element beans= doc.getDocumentElement(); //beans tag
			System.out.println(beans.getTagName());
			NodeList list = beans.getElementsByTagName("bean");
			
			
			for(int i=0;i<list.getLength(); i++){
				Element bean = (Element)list.item(i);
		
				System.out.println(bean.getTagName());
				
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				Class clsName = Class.forName(cls);
				Object obj = clsName.newInstance();		//객체 주소 넘기기 
				
				//컨트롤러 어노테이션의 null 값 확인 
				//Controller con = (Controller)clsName.getAnnotation(Controller.class);
				
				if(clsName.isAnnotationPresent(Controller.class)==false) //어노테이션이 존재하지않으면 pass
					continue;
				System.out.println("id="+id);
				System.out.println("model="+obj);

				clsMap.put(id, obj);
				//continue로 제외
			}
			//객체 생성한후 반복 사용(싱글톤)
		} catch (Exception e) {	}
	}

	//http://localhost/MVCProject3/*.do
	///MVCProject3/*.do 		----> Context 부분
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		cmd = cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		//사용자 요청
		
		//사용자 요청 처리 => Model 
		Model model = (Model)clsMap.get(cmd);
		//요청 처리
		String jsp = model.handlerRequest(request);  //받은 jsp의 이름 
		/*
		 *   	return "파일명" =>  forward
		 *  	return "redirect:" =>
		 		
		 		substring(start,end)	start 포함, end포함 안되어있음
		 		
		 		/MVCProject4/list.do?id=12
		 		=>  URI => ?~ 뒤에 내용은 포함하지 않고  request에 넣어줌
		 		
		 */
		
		//화면이동, Request 전송
		if(jsp.startsWith("redirect")){
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
		}else{
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		
	}

}

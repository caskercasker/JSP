package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ListModel {
	public void execute(HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		
		request.setAttribute("list", list);
		//Controller 전송 => JSP로 전송
		
	}
}

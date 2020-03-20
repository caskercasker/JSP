package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
public class SawonModel {
	public void sawonListData(HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		list.add("박");
		list.add("김");
		list.add("이");
		
		request.setAttribute("list"	, list);
	}
}

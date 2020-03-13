package com.sist.temp;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class SawonManager {
	public void sawonAllData(HttpServletRequest request){
		List<Sawon> list = new ArrayList<Sawon>();
		
		list.add(new Sawon(1,"조우현","영업부"));
		list.add(new Sawon(2,"강감찬","인재부"));
		list.add(new Sawon(3,"심청이","개발부"));
		list.add(new Sawon(4,"김고동","기획부"));
		list.add(new Sawon(5,"감사장","총무부"));
		
		//request. 추가
		request.setAttribute("list", list);
		// 					  name   value 
		// ?name=attr1&value=value1
		//call by reference 값을 주소에 실어서 보낸다. 
		
	}
}

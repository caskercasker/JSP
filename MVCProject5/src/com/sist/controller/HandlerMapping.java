package com.sist.controller;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*; //File
	
public class HandlerMapping {
	
	private List<String> list = 
			new ArrayList<String>();
	
	//web.xml 의 경로 -> String path 
	public HandlerMapping(String path, String defaultPath){
		try {
			
			SAXParserFactory spf =SAXParserFactory.newInstance();
			//Sax파서기
			SAXParser sp = spf.newSAXParser();
			XMLParser xp = new XMLParser();
			sp.parse(new File(path), xp);
			List<String> pList=xp.getList();

			ComponentScan cs = new ComponentScan();
			for(String pack:pList){
				List<String> fNames = cs.getClassNameRead(pack, defaultPath);
				for(String name:fNames){
					list.add(name);
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		//io 처리는 Exception 을 주어야 한다. 
		/*
		 * https://blog.naver.com/serverwizard/220789097495
		 */
		
	}
	
	public List<String> getList() {
		return list;
	} 
	
	
}

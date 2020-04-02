package com.sist.controller;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*		DefaultHandler
 *  
 *		SAX => 읽기 전용 
 *		<?xml version="1.0"?>  ==> startDocument
 *		<book>			=> startElement
 *			<list>		=> startElement
 *				<author> 	=> startElement
 *					sss		=> characters
 *				</author>	=> endElement
 *				<title>		=> startElement
 *					aaaa	=> characters
 *				</title>	=> endElement
 *			</list>			=> endElement
 *		</book> 			=> endElement
 *							=> endDocument
 *		//한줄을 읽을때 마다 메소드 호출 처리
 *		
 */

public class XMLParser extends DefaultHandler{

	private List<String> list = new ArrayList<String>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("context:component-scan"))		//qName - tagName
			{
				String pack = attributes.getValue("base-package"); //속성명 attributes 속성의 이름으로 접근
				System.out.println(pack);
				list.add(pack);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<String> getList() {
		return list;
	}
	
}

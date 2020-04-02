package com.sist.controller;
import java.util.*;
import java.io.*;

//패키지 내에 있는 Java "파일"을 읽어서 이름을 가져온다.
// --> Class cls = Class.forname("com.sist.model.BoardModel")로 생성한다. 
public class ComponentScan {
	public List<String> getClassNameRead(String pack, String path){
		List<String> list = new ArrayList<String>();
		try{
			//String path="C:\\Users\\sist\\git\\JSP\\MVCProject5\\src";
			path = path+"\\"+pack.replace(".", "\\");
			File dir=new File(path);  //디렉토리에 있는 모든 파일을 가져오고
			File[] files = dir.listFiles();
			
			for(File f:files){
				String name=f.getName();

				//확장자가 java인 경우
				if(name.endsWith("java")){   
					String s = pack+"."+name.substring(0,name.indexOf("."));
					// name = "BoardModel.java"
					// s = "com.sist.model.BoardModel"
					list.add(s);
				}
			}
			//com.sist.model.BoardModel --> com\\sist\\model\\BoardModel
			//확장자가 property를 제외하고 .java중에 interface를 제외하고 가져와야 한다.
			//but interface를 통해 model들을 관리하는 것이 아니라 어노테이션으로 구분하기 때문에 필요 없다. 
			
		}catch(Exception ex){}
		return list;
	}
}

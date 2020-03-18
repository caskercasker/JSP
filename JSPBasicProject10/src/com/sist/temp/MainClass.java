package com.sist.temp;

import java.lang.reflect.Method;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("입력:");
			String str=scan.next();			
			
			Class<?> acls = Class.forName("com.sist.temp.A");	//리플렉션 // 메모리 할당 
			//패키지 명으로 부터 출발 class 까지 
//			A a = (A)acls.newInstance();
//			a.display();
			Object obj = acls.newInstance();
			
			Method[] m = acls.getDeclaredMethods();
//			m[0].invoke(obj,null);
			
			for(Method mm:m){
				RequestMapping rm=mm.getDeclaredAnnotation(RequestMapping.class);
				if(rm.value().equals(str)){
					mm.invoke(obj, null);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

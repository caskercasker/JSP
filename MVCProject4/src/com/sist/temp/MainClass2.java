package com.sist.temp;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import com.sist.temp.RequestMapping;

class A 
{
	@RequestMapping("a.do")
	public void aaa(){
		System.out.println("A:aaa() Call....");
	}
	
	@RequestMapping("b.do")
	public void bbb(){
		System.out.println("A:bbb() Call....");
	}
	
	@RequestMapping("c.do")
	public void ccc(){
		System.out.println("A:ccc() Call....");
	}
	
	@RequestMapping("d.do")
	public void ddd(){
		System.out.println("A:ddd() Call....");
	}
	
	@RequestMapping("e.do")
	public void eee(){
		System.out.println("A:eee() Call....");
	}
}


public class MainClass2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		//a.do => aaa b.do => bbb....
		System.out.println("URI주소 입력:");
		String uri=scan.next();
		
		try {
			//1. 메모리 할당 
			Class clsName = Class.forName("com.sist.temp.A");
			Object obj = clsName.newInstance(); //객체 생성
			//A a = new A();
			
			Method[] methods = clsName.getDeclaredMethods();
			for(Method method: methods ){
				RequestMapping rm = method.getAnnotation(RequestMapping.class);
				if(rm.value().equals(uri)){
					method.invoke(obj, null);		
				}
				System.out.println(method.getModifiers());
				System.out.println(method.getName());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
/*		A a = new A();
		if(uri.equals("a.do")){
			a.aaa();
		}else if(uri.equals("b.do")){
			a.bbb();
		}else if(uri.equals("c.do")){
			a.ccc();
		}else if(uri.equals("d.do")){
			a.ddd();
		}else if(uri.equals("e.do")){
			a.eee();
		}
			
		*/
	}
}

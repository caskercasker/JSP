<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.net.*"%>
<%
	
	String fn=request.getParameter("fn");
	
	response.setHeader("Content-Disposition", "attachment;filename="
						+URLEncoder.encode(fn, "UTF-8"));
	
	//header의 기준 urlEndcodder 호출로 enccoding 한다 (fn이라는 문자열을 UTF-8 기준으로.)
	//헤더의 컨텐츠 위치와, 파일 위치; 파일 이름 
	
	File file=new File("c:\\upload\\"+fn);
	response.setContentLengthLong((int)file.length());
	
	try{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		int i=0;
		byte[] buffer = new byte[1024];
		while((i=bis.read(buffer,0,1024))!=-1){
			bos.write(buffer,0,i);
		}
		
		out.clear();
		out=pageContext.pushBody(); 
		//객체 초기화 pageContext 원래 상태로 복귀
		
		bis.close();
		bos.close();
	}catch(Exception ex){}
%>